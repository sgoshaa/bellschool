package com.bell.bellschooll.exception;

/**
 * Исключение, которое мы бросаем,при ошибках пользователя
 */
public class anyUserErrorException extends RuntimeException {
    public anyUserErrorException(String message) {
        super(message);
    }
}
