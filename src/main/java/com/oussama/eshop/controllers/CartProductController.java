package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.dto.requests.AddProductRequest;
import com.oussama.eshop.domain.dto.responses.ApiResponse;
import com.oussama.eshop.domain.dto.responses.CartProductsResponse;
import com.oussama.eshop.domain.dto.requests.IdRequest;
import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.*;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cartProduct")
@RequiredArgsConstructor
public class CartProductController {

    private final CartProductService cartProductService;
    private final CartService cartService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final Mapper<Product, ProductDto> productMapper;
    private final Mapper<Cart, CartDto> cartMapper;


    @GetMapping("/all")
    public ResponseEntity<List<CartProduct>> getAllCartProducts() {
        return new ResponseEntity<>(cartProductService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<?>> getUserCart(Authentication auth) {
        CustomerDto customer = customerService.findByEmail(auth.getName());
        List<CartProduct> foundProducts = cartProductService.findCartProductsByCustomer(customer.getId());
        List<?> list = foundProducts.stream()
                .map(cartProduct -> CartProductsResponse.builder()
                        .id(cartProduct.getProduct().getId())
                        .name(cartProduct.getProduct().getName())
                        .imageUrl(cartProduct.getProduct().getImageUrl())
                        .quantity(cartProduct.getQuantity())
                        .build()).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/getProducts")
    public ResponseEntity<List<CartProductsResponse>> showProductsInCart(@RequestBody IdRequest request) {
        boolean cartExists = cartService.exists(request.id());
        if (cartExists) {
            List<CartProduct> cartProductByCartId = cartProductService.findCartProductByCartId(request.id());
            List<CartProductsResponse> list = cartProductByCartId.stream().map(cartProduct -> {
                CartProductsResponse response = new CartProductsResponse(cartProduct.getQuantity());
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
        return new ResponseEntity<>(new ApiResponse("Product added to cart successfully", true), HttpStatus.OK);

    }
}

