package com.oussama.eshop.controllers;

import com.oussama.eshop.common.requests.AddProductRequest;
import com.oussama.eshop.common.responses.ApiResponse;
import com.oussama.eshop.common.responses.GetProductsResponse;
import com.oussama.eshop.common.requests.IdRequest;
import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.domain.entities.CartProductKey;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.services.CartProductService;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;
    private final CartService cartService;
    private final ProductService productService;

    private final Mapper<Product,ProductDto> productMapper;
    private final Mapper<Cart, CartDto> cartMapper;

    public CartProductController(CartProductService cartProductService, CartService cartService, ProductService productService, Mapper<Product, ProductDto> productMapper, Mapper<Cart, CartDto> cartMapper) {
        this.cartProductService = cartProductService;
        this.cartService = cartService;
        this.productService = productService;
        this.productMapper = productMapper;
        this.cartMapper = cartMapper;
    }

    @GetMapping
    public ResponseEntity<List<CartProduct>> getAllCartProducts() {
        return new ResponseEntity<>(cartProductService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getProducts")
    public ResponseEntity<List<GetProductsResponse>> showProductsInCart(@RequestBody IdRequest request) {
        boolean cartExists = cartService.exists(request.id());
        if (cartExists) {
            List<CartProduct> cartProductByCartId = cartProductService.findCartProductByCartId(request.id());
            List<GetProductsResponse> list = cartProductByCartId.stream().map(cartProduct -> {
                GetProductsResponse response = new GetProductsResponse(cartProduct.getQuantity());
                response.setId(cartProduct.getProduct().getId());
                response.setName(cartProduct.getProduct().getName());
                response.setImageUrl(cartProduct.getProduct().getImageUrl());
                return response;
            }).toList();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else throw new EntityNotFoundException("Cart not found with id: " + request.id());
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddProductRequest request) {

        // Retrieve product and cart entities
        ProductDto product = productService.findOne(request.productId());
        CartDto cart = cartService.findOne(request.cartId());

        // Create a new CartProduct entity
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(productMapper.mapFrom(product));
        cartProduct.setCart(cartMapper.mapFrom(cart));
        cartProduct.setQuantity(request.quantity());

        // Set the embedded key
        CartProductKey cartProductKey = new CartProductKey(cart.getId(), product.getId());
        cartProduct.setCartProductKey(cartProductKey);

        // Save the CartProduct entity
        cartProductService.save(cartProduct);
        return new ResponseEntity<>(new ApiResponse("Product added to cart successfully",true),HttpStatus.OK);

    }
}

