package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Request для обновления организации
 */
@Data
public class OrganizationUpdateInDto {
    /**
     * id организации
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer id;
    /**
     * Название организации
     */
    @NotBlank(message = "Название - обязательное поле")
    private String name;
    /**
     * Полное название организации
     */
    @NotBlank
    private String fullName;
    /**
     * ИНН
     */
    @NotNull
    private Integer inn;
    /**
     * КПП
     */
    @NotNull
    private Integer kpp;
    /**
     * Адрес организации
     */
    @NotBlank
    private String address;
    /**
     * Телефон
     */
    private String phone;
    /**
     * Поле isActive
     */
    @NotNull(message = "по умолчанию true")
    private Boolean isActive;
}
