package com.oussama.eshop.controllers;

import com.oussama.eshop.controllers.requests.AuthReq;
import com.oussama.eshop.controllers.requests.RegisterReq;
import com.oussama.eshop.controllers.responses.AuthRes;
import com.oussama.eshop.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AuthRes> signup(
            @RequestBody RegisterReq request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthRes> login(
            @RequestBody AuthReq request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
