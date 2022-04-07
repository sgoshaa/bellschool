package com.bell.bellschooll.dto.response;

import lombok.Data;

/**
 * Response DTO для успешных ответов
 */
@Data
public class SuccessDto {
    /**
     * поле результат
     */
    private String result = "success";
}
