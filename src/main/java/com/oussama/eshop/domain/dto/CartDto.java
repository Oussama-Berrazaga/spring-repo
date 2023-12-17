package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private Integer id;

    private List<CartProduct> cartProducts;
}
