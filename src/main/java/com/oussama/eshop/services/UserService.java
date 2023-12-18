package com.oussama.eshop.services;

import com.oussama.eshop.controllers.requests.ChangePasswordReq;
import com.oussama.eshop.domain.dto.UserDto;
import com.oussama.eshop.domain.entities.User;
import jakarta.validation.constraints.Email;

import java.security.Principal;
import java.util.List;


public interface UserService {

    List<UserDto> findAll();


    UserDto findById(Integer id);

    UserDto findByEmail(String email);

    void changePassword(ChangePasswordReq request, Principal connectedUser);
}
