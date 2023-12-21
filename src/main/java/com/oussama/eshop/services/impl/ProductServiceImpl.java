package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.exceptions.CustomException;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.ProductRepository;
import com.oussama.eshop.services.ProductService;
import com.oussama.eshop.specifications.EntitySpecification;
import com.oussama.eshop.controllers.requests.FindRequest;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Mapper<Product, ProductDto> mapper;
    private final EntitySpecification<Product> entitySpecification;

//    @PostConstruct
//    public void initDB() {
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> Product.builder()
//                        .name("Product " + i)
//                        .imageUrl("https://example.com/product_" + i + ".jpg")
//                        .price(new Random().nextLong(1000L, 9999L))
//                        .build())
//                .collect(Collectors.toList());
//        productRepository.saveAll(products);
//    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(mapper::mapTo).toList();
    }

    @Override
    public ProductDto findOne(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return mapper.mapTo(product);
    }


    @Override
    public boolean exists(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return mapper.mapTo(productRepository.save(mapper.mapFrom(productDto)));
    }

    @Override
    public void delete(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else throw new EntityNotFoundException("Product not found with id: " + id);
    }

    @Override
    public ProductDto fullUpdate(ProductDto productDto) {
        if (productRepository.existsById(productDto.getId())) {
            return mapper.mapTo(productRepository.save(mapper.mapFrom(productDto)));
        } else throw new EntityNotFoundException("Product not found with id: " + productDto.getId());
    }

    @Override
    public ProductDto partialUpdate(ProductDto productDto) {
        if (productRepository.existsById(productDto.getId())) {
            return mapper.mapTo(productRepository.save(mapper.mapFrom(productDto)));
        } else throw new EntityNotFoundException("Product not found with id: " + productDto.getId());
    }

    @Override
    public List<ProductDto> findProductsWithSorting(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field)).stream().map(mapper::mapTo).toList();
    }

    @Override
    public List<ProductDto> findProductsWithPagination(int offset, int pageSize) {
        return productRepository.findAll(PageRequest.of(offset, pageSize)).stream().map(mapper::mapTo).toList();
    }

    @Override
    public List<ProductDto> findProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field))).stream().map(mapper::mapTo).toList();
    }

    @Override
    public ProductDto findProduct(Integer id, String name, Long price) {
        if (id != null) {
            return mapper.mapTo(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id)));
        }
        if (name != null) {
            return mapper.mapTo(productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Product not found with name: " + name)));
        }
        if (price != null) {
            return mapper.mapTo(productRepository.findByPrice(price).orElseThrow(() -> new EntityNotFoundException("Product not found with price: " + price)));
        }
        return new ProductDto();
    }

    @Override
    public List<ProductDto> findDynamicProducts(FindRequest req) {
//        SearchCriteria search = SearchCriteria.builder()
//                .filters(List.of(SearchCriteria.Filter.builder()
//                        .field("name")
//                        .operator(
//                                SearchCriteria.Filter.QueryOperator.EQUALS)
//                        .value("Product 1")
//                        .build()))
//                .build();

        return productRepository.findAll(entitySpecification.specificationBuilder(req)).stream().map(mapper::mapTo).toList();
    }

    @Override
    public List<ProductDto> findDynamicProductsWithPagination(FindRequest req, int page, int size) {
        if (page < 1)
            page = 1;
        if (size < 1)
            size = 10;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        var products = productRepository.findAll(entitySpecification.specificationBuilder(req), pageable);
        return products.stream().map(mapper::mapTo).toList();
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        Calendar calendar = Calendar.getInstance();
        if (file.isEmpty()) {
            throw new CustomException("Empty file");
        }
        Path destination = Paths.get("uploads").resolve(calendar.getTimeInMillis() + "_" + file.getOriginalFilename()).normalize().toAbsolutePath();
        Files.copy(file.getInputStream(), destination);
        return destination.toString();
       // return "http://localhost:8080/uploads/" + calendar.getTimeInMillis() + "_" + file.getOriginalFilename();
    }
}
