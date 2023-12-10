package com.oussama.eshop.common.responses;

import java.time.LocalDateTime;

public record ApiResponse (String message, boolean success){
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
