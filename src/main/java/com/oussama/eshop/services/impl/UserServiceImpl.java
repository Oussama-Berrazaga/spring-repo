package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.requests.ChangePasswordRequest;
import com.oussama.eshop.domain.dto.UserDto;
import com.oussama.eshop.domain.entities.User;
import com.oussama.eshop.domain.enums.Role;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.UserRepository;
import com.oussama.eshop.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${application.security.accounts.admin.email}")
    private String ADMIN_EMAIL;
    @Value("${application.security.accounts.admin.password}")
    private String ADMIN_PASSWORD;
    @Value("${application.security.accounts.admin.firstname}")
    private String ADMIN_FIRSTNAME;
    @Value("${application.security.accounts.admin.lastname}")
    private String ADMIN_LASTNAME;
    @Value("${application.security.accounts.test.email}")
    private String TEST_EMAIL;
    @Value("${application.security.accounts.test.password}")
    private String TEST_PASSWORD;
    @Value("${application.security.accounts.test.firstname}")
    private String TEST_FIRSTNAME;
    @Value("${application.security.accounts.test.lastname}")
    private String TEST_LASTNAME;

    private final UserRepository userRepository;
    private final Mapper<User, UserDto> mapper;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDB() {
        var savedAdmin = userRepository.findByEmail(ADMIN_EMAIL);
        var savedTestUser =  userRepository.findByEmail(TEST_EMAIL);
        if (savedAdmin.isEmpty()) {
            var newAdmin = User.builder()
                    .firstName(ADMIN_FIRSTNAME)
                    .lastName(ADMIN_LASTNAME)
                    .email(ADMIN_EMAIL)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(newAdmin);
        }
        if (savedTestUser.isEmpty()) {
            var newTestUser = User.builder()
                    .firstName(TEST_FIRSTNAME)
                    .lastName(TEST_LASTNAME)
                    .email(TEST_EMAIL)
                    .password(passwordEncoder.encode(TEST_PASSWORD))
                    .role(Role.CUSTOMER)
                    .build();
            userRepository.save(newTestUser);
        }
    }

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

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

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
