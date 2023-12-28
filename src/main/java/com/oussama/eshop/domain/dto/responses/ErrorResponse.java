package com.oussama.eshop.domain.dto.responses;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public record ErrorResponse(String message, String exception, boolean success) {
    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        var sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}
