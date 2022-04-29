package com.bell.bellschooll.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Request DTO для сохранения новой организации
 */
@Data
public class OrganizationSaveInDto {
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
     * номер телеофна организации
     */
    private String phone;
    /**
     * Поле isActive
     */
    private Boolean isActive;
}
