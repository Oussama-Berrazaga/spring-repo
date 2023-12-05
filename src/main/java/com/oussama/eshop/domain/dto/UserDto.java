package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.entities.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Cart cart;
}
