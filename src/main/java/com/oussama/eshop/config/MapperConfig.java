package com.oussama.eshop.config;

import com.oussama.eshop.controllers.responses.ApiResponse;
import com.oussama.eshop.exceptions.CustomException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler
        public ResponseEntity<ApiResponse> handleCustomException(final CustomException customException) {
            return new ResponseEntity<>(new ApiResponse(customException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler
        public ResponseEntity<ApiResponse> handleNullPointerException(final NullPointerException nullPointerException) {
            return new ResponseEntity<>(new ApiResponse(nullPointerException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiResponse> handleConstraintViolationException(final ConstraintViolationException constraintViolationException){
            return new ResponseEntity<>(new ApiResponse(constraintViolationException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiResponse> handleEntityNotFoundException(final EntityNotFoundException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiResponse> handleDuplicateKeyException(final DuplicateKeyException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }
}