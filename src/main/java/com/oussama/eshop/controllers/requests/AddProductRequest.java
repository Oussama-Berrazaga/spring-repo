package com.oussama.eshop.controllers.requests;

public record AddProductRequest(Integer cartId, Integer productId, Integer quantity) {
}
