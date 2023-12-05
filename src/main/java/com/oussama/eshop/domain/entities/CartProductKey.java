package com.oussama.eshop.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class CartProductKey implements Serializable {

    @Column(name = "product_id")
    Integer productId;

    @Column(name = "cart_id")
    Integer cartId;
}
