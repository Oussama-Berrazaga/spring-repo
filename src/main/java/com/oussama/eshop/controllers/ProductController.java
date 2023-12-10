package com.oussama.eshop.controllers;

import com.oussama.eshop.common.responses.ApiResponse;
import com.oussama.eshop.common.requests.IdRequest;
import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    record ResponseWrapper(List<?> data, boolean status) {
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProduct(@RequestBody IdRequest requestBody) {
        productService.delete(requestBody.id());
        return new ResponseEntity<>(new ApiResponse("Product has been deleted", true), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> findProductById(@RequestBody IdRequest request) {
        return new ResponseEntity<>(productService.findOne(request.id()), HttpStatus.OK);
    }
    @Transactional
    @PutMapping
    public ResponseEntity<ProductDto> fullUpdateProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.fullUpdate(product), HttpStatus.OK);
    }
    @Transactional
    @PatchMapping
    public ResponseEntity<ProductDto> partialUpdateProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.partialUpdate(product), HttpStatus.OK);
    }
}
