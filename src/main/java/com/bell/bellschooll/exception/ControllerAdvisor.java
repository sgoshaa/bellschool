package com.bell.bellschooll.exception;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponseException> handlerErrorException(ErrorException exception) {
        ErrorResponseException errorResponse = new ErrorResponseException(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseException> handlerException(Exception exception) {
        String stringError = getRandomString(exception.getMessage());
        log.error(stringError);
        ErrorResponseException errorResponse = new ErrorResponseException(stringError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField()+" : "+fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        ErrorResponseException errorResponse = new ErrorResponseException(list.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getRandomString(String message) {
        return RandomStringUtils.randomAlphanumeric(10) + " | " + LocalDate.now() + " | " + message;
    }
}
