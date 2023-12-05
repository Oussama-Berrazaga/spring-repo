package com.oussama.eshop.controllers;

import com.oussama.eshop.exceptions.CustomException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    record ErrorBody(String error, boolean status) {
    }

    @ExceptionHandler
    public ResponseEntity handleCustomException(final CustomException customException) {
        return new ResponseEntity<>(new ErrorBody(customException.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleNullPointerException(final NullPointerException nullPointerException) {
        return new ResponseEntity<>(new ErrorBody(nullPointerException.getMessage(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(final ConstraintViolationException constraintViolationException){
        return new ResponseEntity<>(new ErrorBody(constraintViolationException.getMessage(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity handleEntityNotFoundException(final EntityNotFoundException exception){
        return new ResponseEntity<>(new ErrorBody(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
    }


}
