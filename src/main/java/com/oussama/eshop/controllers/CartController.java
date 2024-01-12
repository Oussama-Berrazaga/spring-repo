package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.services.CartService;

import java.util.List;
import java.util.Optional;

import com.oussama.eshop.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Carts")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> findAllCarts() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

}
