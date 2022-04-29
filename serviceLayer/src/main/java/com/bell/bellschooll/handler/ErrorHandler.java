package com.bell.bellschooll.handler;

import com.bell.bellschooll.exception.ErrorResponse;
import com.bell.bellschooll.exception.anyUserErrorException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ErrorHandler для обработки исключений
 */
@ControllerAdvice
@Log4j2
public class ErrorHandler extends ResponseEntityExceptionHandler {
    /**
     * Метод для обработки исключений типа ErrorException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(anyUserErrorException.class)
    public ResponseEntity<ErrorResponse> handlerErrorException(anyUserErrorException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Метод для  обработки исключений типа Exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception exception) {
        String stringError = getRandomString(exception.getMessage());
        log.error(stringError);
        ErrorResponse errorResponse = new ErrorResponse(stringError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Метод для обработки исключений валидации
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse(list.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getRandomString(String message) {
        return RandomStringUtils.randomAlphanumeric(10) + " | " + LocalDate.now() + " | " + message;
    }
}
