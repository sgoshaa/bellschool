package com.bell.bellschooll.dto.request;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Request DTO для сохранение нового офиса
 */
@Data
public class OfficeInSaveDto {
    /**
     * Id организации
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer orgId;
    /**
     * Название офиса
     */
    private String name;
    /**
     * Телефон офиса
     */
    private String phone;
    /**
     * Адрес офиса
     */
    private String address;
    /**
     * Поле isActive
     */
    private Boolean isActive;
}
