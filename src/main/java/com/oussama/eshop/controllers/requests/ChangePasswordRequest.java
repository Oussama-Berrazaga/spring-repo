package com.oussama.eshop.controllers.requests;

public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmationPassword) {

}
