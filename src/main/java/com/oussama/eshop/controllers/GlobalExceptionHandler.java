package com.oussama.eshop.controllers;

import com.oussama.eshop.controllers.responses.ErrorRes;
import com.oussama.eshop.exceptions.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleCustomException(final CustomException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleNullPointerException(final NullPointerException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleConstraintViolationException(final ConstraintViolationException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleEntityNotFoundException(final EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleDuplicateKeyException(final DuplicateKeyException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleBadCredentialsException(final BadCredentialsException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleUsernameNotFoundException(final UsernameNotFoundException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleExpiredJwtException(final ExpiredJwtException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleIllegalArgumentException(final IllegalArgumentException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleMalformedJwtException(final MalformedJwtException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorRes> handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new ErrorRes(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

}
