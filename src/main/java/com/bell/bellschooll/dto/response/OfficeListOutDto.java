package com.bell.bellschooll.dto.response;

import lombok.Data;

/**
 * Response DTO для вывод списка офисов
 */
@Data
public class OfficeListOutDto {
    /**
     * id офиса
     */
    private Integer id;
    /**
     * Название офиса
     */
    private String name;
    /**
     * Поле isActive
     */
    private Boolean isActive;
}
