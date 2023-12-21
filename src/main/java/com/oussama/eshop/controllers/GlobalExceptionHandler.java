package com.oussama.eshop.controllers;

import com.oussama.eshop.controllers.responses.ErrorResponse;
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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCustomException(final CustomException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNullPointerException(final NullPointerException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(final DuplicateKeyException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(final BadCredentialsException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(final UsernameNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(final ExpiredJwtException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(final MalformedJwtException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(final MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getClass().getName(), false), HttpStatus.BAD_REQUEST);
    }

}
