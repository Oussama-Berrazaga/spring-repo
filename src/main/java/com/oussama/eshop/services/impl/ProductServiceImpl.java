package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.Product;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.ProductRepository;
import com.oussama.eshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Mapper<Product, ProductDto> mapper;

    public ProductServiceImpl(ProductRepository productRepository, Mapper<Product, ProductDto> mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

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

}
