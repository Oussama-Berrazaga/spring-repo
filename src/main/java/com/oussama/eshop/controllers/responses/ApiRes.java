package com.oussama.eshop.controllers.responses;

import java.time.LocalDateTime;

public record ApiRes(String message, boolean success){
    public String getDate() {
        return LocalDateTime.now().toString();
    }
}
