package com.oussama.eshop.services;

import com.oussama.eshop.controllers.requests.AuthRequest;
import com.oussama.eshop.controllers.requests.RegisterRequest;
import com.oussama.eshop.controllers.responses.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest req);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
