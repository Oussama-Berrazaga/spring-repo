package com.oussama.eshop.services;

import com.oussama.eshop.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAll();
    Optional<Product> findOne(Integer id);
    Product save(Product product);

    void delete(Integer id);

    Product update(Product product);
}
