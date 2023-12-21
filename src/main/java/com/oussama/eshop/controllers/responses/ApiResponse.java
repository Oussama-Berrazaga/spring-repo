package com.oussama.eshop.controllers.responses;

import java.time.LocalDateTime;

public record ApiResponse(String message, boolean success){
    public String getDate() {
        return LocalDateTime.now().toString();
    }
}
