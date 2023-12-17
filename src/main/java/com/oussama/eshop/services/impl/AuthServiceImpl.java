package com.oussama.eshop.services.impl;

import com.oussama.eshop.config.JwtService;
import com.oussama.eshop.controllers.requests.AuthReq;
import com.oussama.eshop.controllers.requests.RegisterReq;
import com.oussama.eshop.controllers.responses.AuthRes;
import com.oussama.eshop.domain.dto.UserDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.domain.entities.Token;
import com.oussama.eshop.domain.entities.User;
import com.oussama.eshop.domain.enums.Role;
import com.oussama.eshop.domain.enums.TokenType;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.CartRepository;
import com.oussama.eshop.repositories.CustomerRepository;
import com.oussama.eshop.repositories.TokenRepository;
import com.oussama.eshop.repositories.UserRepository;
import com.oussama.eshop.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Mapper<User, UserDto> mapper;

    public AuthRes register(RegisterReq request) {
        Customer user = Customer.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();
        Cart cart = cartRepository.save(new Cart());
        user.setCart(cart);
        User savedUser = customerRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthRes.builder()
                .token(jwtToken)
                .user(mapper.mapTo(user))
                .build();
    }

    public AuthRes authenticate(AuthReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User " + request.email() + " is not found"));
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthRes.builder()
                .token(jwtToken)
                .user(mapper.mapTo(user))
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
