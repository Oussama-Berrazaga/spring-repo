package com.oussama.eshop.common.responses;

import com.oussama.eshop.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponse extends Product {
    Integer quantity;
}
