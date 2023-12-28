package com.oussama.eshop.domain.dto.requests;

public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmationPassword) {

}
