package com.oussama.eshop.controllers.responses;

import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.domain.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartProductsRes extends Product {
    Integer quantity;

    public CartProductsRes(Integer id, @NotNull String name, String imageUrl, List<CartProduct> cartProducts, Integer quantity) {
        super(id, name, imageUrl, cartProducts);
        this.quantity = quantity;
    }
}
