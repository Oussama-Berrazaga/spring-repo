package com.oussama.eshop.controllers;

import com.oussama.eshop.controllers.requests.ProductReq;
import com.oussama.eshop.controllers.responses.ApiRes;
import com.oussama.eshop.controllers.requests.IdReq;
import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.repositories.ProductRepository;
import com.oussama.eshop.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;



    record ResponseWrapper(List<?> data, boolean status) {
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductReq req) {
        System.out.println(req);
        //return ResponseEntity.ok("Hello");
        //return ResponseEntity.ok(Product.builder().id(1).name("Prod1").imageUrl("url").build());
        //return new ResponseEntity<>(productRepository.save(req),HttpStatus.CREATED);
        return new ResponseEntity<>(productService.save(ProductDto.builder().name(req.getName()).imageUrl(req.getImageUrl()).build()), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiRes> deleteProduct(@RequestBody IdReq requestBody) {
        productService.delete(requestBody.id());
        return new ResponseEntity<>(new ApiRes("Product has been deleted", true), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ProductDto> findProductById(@RequestBody IdReq request) {
//        return ResponseEntity.ok(productService.findOne(request.id()));
//    }
    @Transactional
    @PutMapping
    public ResponseEntity<ProductDto> fullUpdateProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.fullUpdate(product));
    }
    @Transactional
    @PatchMapping
    public ResponseEntity<ProductDto> partialUpdateProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.partialUpdate(product), HttpStatus.OK);
    }
}
