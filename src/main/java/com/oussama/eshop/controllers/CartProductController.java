package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.domain.entities.CartProductKey;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.exceptions.CustomException;
import com.oussama.eshop.services.CartProductService;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;
    private final CartService cartService;
    private final ProductService productService;

    public CartProductController(CartProductService cartProductService, CartService cartService, ProductService productService) {
        this.cartProductService = cartProductService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<CartProduct>> getAllCartProducts() {
        return new ResponseEntity<>(cartProductService.findAll(), HttpStatus.OK);
    }

    record AddProductRequest(Integer cartId, Integer productId, Integer quantity) {
    }

    @PostMapping("/add")
    public ResponseEntity addProductToCart(@RequestBody AddProductRequest request) {
        Optional<Cart> foundCart = cartService.findOne(request.cartId());
        Optional<Product> foundProduct = productService.findOne(request.productId());
        Cart cart = foundCart.orElseThrow(() -> new CustomException("Cart not found"));
        Product product = foundProduct.orElseThrow(() -> new CustomException("Product not found"));

        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(request.quantity());
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        //cartProduct.setCartProductKey(new CartProductKey(product.getId(), cart.getId()));
        cartProductService.save(cartProduct);
        return new ResponseEntity<>("Works", HttpStatus.OK);
    }

    record GetProductsRequest(Integer cartId) {
    }

    @PostMapping("/getProducts")
    public ResponseEntity<List<GetProductsResponse>> showProductsInCart(@RequestBody GetProductsRequest request) {
        boolean cartExists = cartService.exists(request.cartId());
        if (cartExists) {
            List<CartProduct> cartProductByCartId = cartProductService.findCartProductByCartId(request.cartId());
            List<GetProductsResponse> list = cartProductByCartId.stream().map(cartProduct -> {

                GetProductsResponse response = new GetProductsResponse(cartProduct.getQuantity());
                response.setId(cartProduct.getProduct().getId());
                response.setName(cartProduct.getProduct().getName());
                response.setImageUrl(cartProduct.getProduct().getImageUrl());
                return response;
            }).toList();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else throw new EntityNotFoundException("Cart not found with id: " + request.cartId());
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody AddProductRequest request) {

        // Retrieve product and cart entities
        Product product = productService.findOne(request.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.productId()));

        Cart cart = cartService.findOne(request.cartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + request.cartId()));

        // Create a new CartProduct entity
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartProduct.setQuantity(50);

        // Set the embedded key
        CartProductKey cartProductKey = new CartProductKey(cart.getId(), product.getId());
        cartProduct.setCartProductKey(cartProductKey);

        // Save the CartProduct entity
        cartProductService.save(cartProduct);

        return ResponseEntity.ok("Product added to cart successfully");

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class GetProductsResponse extends Product {
    Integer quantity;
}