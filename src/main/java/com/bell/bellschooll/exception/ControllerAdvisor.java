package com.bell.bellschooll.exception;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponseException> handlerErrorException(ErrorException exception) {
        ErrorResponseException errorResponse = new ErrorResponseException(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseException> handlerException(Exception exception) {
        String stringError = getRandomString(exception.getMessage());
        System.out.println(stringError);
        ErrorResponseException errorResponse = new ErrorResponseException(stringError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getRandomString(String message) {
        return RandomStringUtils.randomAlphanumeric(10) + " | " + LocalDate.now() + " | " + message;
    }
}
