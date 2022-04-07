package com.bell.bellschooll.dto.response;


import lombok.Data;

/**
 * Response DTO для вывод офиса
 */
@Data
public class OfficeOutDto {
    /**
     * id
     */
    private Integer id;
    /**
     * Название
     */
    private String name;
    /**
     * Адрес
     */
    private String address;
    /**
     * Телефон
     */
    private String phone;
    /**
     * Поле isActive
     */
    private Boolean isActive;
}
