package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.dto.requests.FindProductRequest;
import com.oussama.eshop.domain.dto.requests.IdRequest;
import com.oussama.eshop.domain.dto.requests.ProductRequest;
import com.oussama.eshop.domain.dto.responses.ApiResponse;
import com.oussama.eshop.domain.dto.responses.FileResponse;
import com.oussama.eshop.domain.dto.responses.ListResponse;
import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.services.ProductService;
import com.oussama.eshop.domain.dto.requests.FindRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products")
@Tag(name = "Products")
@Log4j2
public class ProductController {

    private final ProductService productService;

//    @GetMapping
//    public ResponseEntity<ListResponse<List<ProductDto>>> getAllProducts() {
//        List<ProductDto> products = productService.findAll();
//        return new ResponseEntity<>(new ListResponse<>(products.size(), products), HttpStatus.OK);
//    }

    @Operation(
            description = "Get endpoint for sorting products",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    private ListResponse<List<ProductDto>> getProductsWithSort(@RequestParam String field) {
        List<ProductDto> allProducts = productService.findProductsWithSorting(field);
        return new ListResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/pagination")
    private ListResponse<List<ProductDto>> getProductsWithPagination(@RequestParam Integer page, @RequestParam Integer size) {
        if (page != null && size != null && size > 0) {
            List<ProductDto> productsWithPagination = productService.findProductsWithPagination(page, size);
            return new ListResponse<>(productsWithPagination.size(), productsWithPagination);
        }
        List<ProductDto> products = productService.findAll();
        return new ListResponse<>(products.size(), products);
    }

    @GetMapping("/paginationAndSort")
    private ResponseEntity<ListResponse<List<ProductDto>>> getProductsWithPaginationAndSort(@RequestParam int page, @RequestParam int size, @RequestParam String field) {
        List<ProductDto> productsWithPagination = productService.findProductsWithPaginationAndSorting(page, size, field);
        return ResponseEntity.ok(new ListResponse<>(productsWithPagination.size(), productsWithPagination));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest req) {
        return new ResponseEntity<>(productService.save(ProductDto.builder().name(req.getName()).imageUrl(req.getImageUrl()).price(req.getPrice()).build()), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProduct(@RequestBody IdRequest requestBody) {
        productService.delete(requestBody.id());
        return ResponseEntity.ok(new ApiResponse("Product has been deleted", true));
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<ProductDto>> createMultipleProducts(@RequestBody List<ProductRequest> reqList) {
        List<ProductDto> productDtoList = reqList.stream().map(productRequest -> productService.save(ProductDto.builder().name(productRequest.getName()).imageUrl(productRequest.getImageUrl()).price(productRequest.getPrice()).build())).toList();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping
    public ResponseEntity<ProductDto> findProductById(@RequestBody IdRequest request) {
        return ResponseEntity.ok(productService.findOne(request.id()));
    }

    @PostMapping("/find")
    public ResponseEntity<ProductDto> findProduct(@RequestBody FindProductRequest request) {
        return ResponseEntity.ok(productService.findProduct(request.id(), request.name(), request.price()));
    }

    @PostMapping("/findDynamic")
    public ListResponse<List<ProductDto>> findDynamicProduct(@RequestBody FindRequest req) {
        List<ProductDto> products = productService.findDynamicProducts(req);
        return new ListResponse<>(products.size(), products);
    }

    @PostMapping("/findDynamic/pagination")
    private ListResponse<List<ProductDto>> findDynamicProductWithPagination(@RequestBody FindRequest req, @RequestParam int page, @RequestParam int size) {
        List<ProductDto> productsWithPagination = productService.findDynamicProductsWithPagination(req, page, size);
        return new ListResponse<>(productsWithPagination.size(), productsWithPagination);
    }

    @PutMapping
    public ResponseEntity<ProductDto> fullUpdateProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.fullUpdate(product));
    }

    @PatchMapping
    public ResponseEntity<ProductDto> partialUpdateProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.partialUpdate(product));
    }

    @PostMapping("/single-file-upload")
    public ResponseEntity<Map<String,Object>> handleFileUploadUsingCurl(@RequestParam("file") MultipartFile file) {

        Map<String,Object> map = new HashMap<>();

        // Populate the map with file details
        map.put("fileName", file.getOriginalFilename());
        map.put("fileSize", file.getSize());
        map.put("fileContentType", file.getContentType());

        // File upload is successful
        map.put("message", "File upload done");
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        String path= productService.uploadFile(file);
        return new ResponseEntity<>(new FileResponse(path, true), HttpStatus.OK);
    }

}
