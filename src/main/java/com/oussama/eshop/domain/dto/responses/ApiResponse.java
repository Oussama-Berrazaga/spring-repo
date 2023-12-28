package com.oussama.eshop.domain.dto.responses;

import java.time.LocalDateTime;

public record ApiResponse(String message, boolean success){
    public String getDate() {
        return LocalDateTime.now().toString();
    }
}
