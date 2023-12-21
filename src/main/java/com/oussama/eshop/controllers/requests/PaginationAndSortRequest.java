package com.oussama.eshop.controllers.requests;

public record PaginationAndSortRequest(Integer page, Integer offset, String field) {
}
