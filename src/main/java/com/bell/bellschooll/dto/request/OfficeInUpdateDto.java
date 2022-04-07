package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Request DTO для обновления офиса
 */
@Data
public class OfficeInUpdateDto {
    /**
     * id офиса
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer id;
    /**
     * Название офиса
     */
    @NotBlank
    private String name;
    /**
     * Адрес офиса
     */
    @NotBlank
    private String address;
    /**
     * Телефон офиса
     */
    private String phone;
    /**
     * Поле isActive
     */
    private Boolean isActive;
}
