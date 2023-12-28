package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.ProductDto;
import com.oussama.eshop.domain.dto.requests.FindRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findOne(Integer id);

    boolean exists(Integer id);
    ProductDto save(ProductDto product);

    void delete(Integer id);

    ProductDto fullUpdate(ProductDto product);

    ProductDto partialUpdate(ProductDto product);

    List<ProductDto> findProductsWithSorting(String field);

    List<ProductDto> findProductsWithPagination(int offset, int pageSize);

    List<ProductDto> findProductsWithPaginationAndSorting(int offset,int pageSize,String field);

    ProductDto findProduct(Integer id, String name, Long price);

    List<ProductDto> findDynamicProducts(FindRequest findRequest);

    List<ProductDto> findDynamicProductsWithPagination(FindRequest findRequest, int page, int pageSize);

    String uploadFile(MultipartFile file) throws IOException;
}
