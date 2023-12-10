package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<ProductDto> findAll();
    ProductDto findOne(Integer id);

    boolean exists(Integer id);
    ProductDto save(ProductDto product);

    void delete(Integer id);

    ProductDto fullUpdate(ProductDto product);

    ProductDto partialUpdate(ProductDto product);
}
