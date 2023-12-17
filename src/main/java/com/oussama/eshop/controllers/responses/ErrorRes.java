package com.oussama.eshop.controllers.responses;

import java.time.LocalDateTime;

public record ErrorRes (String message, String exception, boolean success){
    public String getDate() {
        return LocalDateTime.now().toString();
    }
}
