package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    record ResponseWrapper(ArrayList data, boolean status){}

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
        //return new ResponseEntity( new ResponseWrapper((ArrayList) productService.findAll(),true) ,HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }


}
