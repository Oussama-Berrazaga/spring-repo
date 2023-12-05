package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.services.CartService;
import java.util.List;
import java.util.Optional;

import com.oussama.eshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;


    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Cart>> findAllCarts(){
        return new ResponseEntity<>(cartService.findAll(),HttpStatus.OK);
    }

}
