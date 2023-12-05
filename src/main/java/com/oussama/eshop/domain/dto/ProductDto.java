package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.entities.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;

    private String name;

    private List<CartProduct> cartProducts;
}
