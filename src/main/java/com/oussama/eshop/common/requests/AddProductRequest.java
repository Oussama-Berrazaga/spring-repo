package com.oussama.eshop.common.requests;

public record AddProductRequest(Integer cartId, Integer productId, Integer quantity) {
}
