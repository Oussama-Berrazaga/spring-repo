package com.oussama.eshop.services.impl;

import com.oussama.eshop.controllers.requests.ChangePasswordReq;
import com.oussama.eshop.domain.dto.UserDto;
import com.oussama.eshop.domain.entities.User;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.UserRepository;
import com.oussama.eshop.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<User, UserDto> mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> findAll() {
     //   return userRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(mapper::mapTo).toList();
        return userRepository.findAllByOrderByIdAsc().stream().map(mapper::mapTo).toList();
    }


    @Override
    public UserDto findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        return mapper.mapTo(user);
    }

    @Override
    public UserDto findByEmail(@Email String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
        return mapper.mapTo(user);
    }

    public void changePassword(ChangePasswordReq request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.newPassword()));

        // save the new password
        userRepository.save(user);

    }

}
