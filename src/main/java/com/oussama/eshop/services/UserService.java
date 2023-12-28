package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.requests.ChangePasswordRequest;
import com.oussama.eshop.domain.dto.UserDto;
import jakarta.validation.constraints.Email;

import java.security.Principal;
import java.util.List;


public interface UserService {

    List<UserDto> findAll();


    UserDto findById(Integer id);

    UserDto findByEmail(@Email String email);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
