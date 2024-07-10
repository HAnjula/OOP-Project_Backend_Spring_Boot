package com.oop.websignup.POS.exception;

import com.oop.websignup.POS.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserExistsExc.class, NotFoundExc.class, InvalidPassExc.class})
    public ResponseEntity<StandardResponse> handleUnauthorizedException(RuntimeException ex) {
        return new ResponseEntity<>(new StandardResponse(401, ex.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

    // Handle other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleException(Exception ex) {
        return new ResponseEntity<>(new StandardResponse(500, "Internal Server Error", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
