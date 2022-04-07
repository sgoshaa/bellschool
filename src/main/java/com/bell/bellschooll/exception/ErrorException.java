package com.bell.bellschooll.exception;

/**
 * Исключение, которое мы бросаем,при ошибках пользователя
 */
public class ErrorException extends RuntimeException{
    public ErrorException(String message){
        super(message);
    }
}
