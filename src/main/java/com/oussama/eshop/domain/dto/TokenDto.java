package com.oussama.eshop.domain.dto;

import com.oussama.eshop.domain.enums.TokenType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TokenDto {
    private Long id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    private boolean expired;

    private boolean revoked;
}
