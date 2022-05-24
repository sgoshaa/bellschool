package com.bell.bellschooll.exception;

/**
 * Исключение, которое мы бросаем,при ошибках пользователя
 */
public class AnyUserErrorException extends RuntimeException {
    public AnyUserErrorException(String message) {
        super(message);
    }
}
