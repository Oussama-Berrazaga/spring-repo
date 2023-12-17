package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.UserDto;
import com.oussama.eshop.domain.entities.User;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.UserRepository;
import com.oussama.eshop.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<User, UserDto> mapper;

    public UserServiceImpl(UserRepository userRepository, Mapper<User, UserDto> mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
        return mapper.mapTo(user);
    }

}
