package com.oussama.eshop.controllers;

import com.oussama.eshop.controllers.requests.AuthRequest;
import com.oussama.eshop.controllers.requests.RegisterRequest;
import com.oussama.eshop.controllers.responses.AuthResponse;
import com.oussama.eshop.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping
    public ResponseEntity<String> testApi(Authentication authentication) {
        if (authentication.getName() != null) {
            String res = "Hello " + authentication.getName();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
