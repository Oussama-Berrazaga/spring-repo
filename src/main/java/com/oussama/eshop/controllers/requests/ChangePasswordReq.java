package com.oussama.eshop.controllers.requests;

public record ChangePasswordReq(String currentPassword, String newPassword, String confirmationPassword) {

}
