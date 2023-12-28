package com.oussama.eshop.domain.dto.responses;

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
public class CartProductsResponse extends Product {
    Integer quantity;

    public CartProductsResponse(Integer id, @NotNull String name, String imageUrl, Long price, List<CartProduct> cartProducts, Integer quantity) {
        super(id, name, imageUrl, price, cartProducts);
        this.quantity = quantity;
    }
}
