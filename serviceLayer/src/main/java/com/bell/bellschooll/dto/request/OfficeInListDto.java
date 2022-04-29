package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Request DTO для фильтрации офисов
 */
@Data
public class OfficeInListDto {
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
     * Поле isActive
     */
    private Boolean isActive;
}
