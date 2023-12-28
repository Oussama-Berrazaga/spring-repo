package com.oussama.eshop.domain.dto.requests;

public record AddProductRequest(Integer cartId, Integer productId, Integer quantity) {
}
