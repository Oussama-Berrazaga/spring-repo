package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.requests.AuthRequest;
import com.oussama.eshop.domain.dto.requests.RegisterRequest;
import com.oussama.eshop.domain.dto.responses.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest req);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
