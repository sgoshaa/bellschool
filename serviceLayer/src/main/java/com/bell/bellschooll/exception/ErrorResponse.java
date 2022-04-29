package com.bell.bellschooll.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response DTO для вывода исключений
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Поле с описание ошибки
     */
    @JsonProperty
    private String error;
}
