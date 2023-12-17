package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.entities.Cart;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDto extends UserDto {
    private CartDto cart;
}
