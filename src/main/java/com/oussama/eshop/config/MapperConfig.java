package com.oussama.eshop.config;

import com.oussama.eshop.controllers.responses.ApiRes;
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
        public ResponseEntity<ApiRes> handleCustomException(final CustomException customException) {
            return new ResponseEntity<>(new ApiRes(customException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler
        public ResponseEntity<ApiRes> handleNullPointerException(final NullPointerException nullPointerException) {
            return new ResponseEntity<>(new ApiRes(nullPointerException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiRes> handleConstraintViolationException(final ConstraintViolationException constraintViolationException){
            return new ResponseEntity<>(new ApiRes(constraintViolationException.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiRes> handleEntityNotFoundException(final EntityNotFoundException exception){
            return new ResponseEntity<>(new ApiRes(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler
        public ResponseEntity<ApiRes> handleDuplicateKeyException(final DuplicateKeyException exception){
            return new ResponseEntity<>(new ApiRes(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }
}