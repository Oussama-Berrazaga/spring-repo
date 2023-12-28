package com.oussama.eshop.domain.dto.requests;

import lombok.Builder;

@Builder
public record AuthRequest(String email, String password) {

}
