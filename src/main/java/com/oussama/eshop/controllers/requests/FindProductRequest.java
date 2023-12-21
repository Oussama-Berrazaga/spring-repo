package com.oussama.eshop.controllers.requests;


public record FindProductRequest(Integer id, String name, Long price) {
}
