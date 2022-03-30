package com.bell.bellschooll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponseException> handlerErrorException(ErrorException exception){
        ErrorResponseException errorResponse = new ErrorResponseException(exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseException> handlerException(Exception exception){
        System.out.println(exception.getMessage());
        ErrorResponseException errorResponse = new ErrorResponseException(exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.OK);
    }
}
