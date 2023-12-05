package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.CartProductKey;
import com.oussama.eshop.domain.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductDto {

    private CartProductKey cartProductKey;

    private Product product;

    private Cart cart;

    private Integer quantity;
}
