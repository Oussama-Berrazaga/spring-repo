package com.oussama.eshop.domain.dto.requests;

public record PaginationAndSortRequest(Integer page, Integer offset, String field) {
}
