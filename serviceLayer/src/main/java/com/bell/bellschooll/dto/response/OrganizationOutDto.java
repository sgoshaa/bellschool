package com.bell.bellschooll.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Response DTO для вывода ораганизации
 */
@Data
public class OrganizationOutDto {
    /**
     * id
     */
    @JsonProperty
    private Integer id;
    /**
     * Название организации
     */
    @JsonProperty
    private String name;
    /**
     * Полное название организации
     */
    @JsonProperty
    private String fullName;
    /**
     * ИНН
     */
    @JsonProperty
    private Integer inn;
    /**
     * КПП
     */
    @JsonProperty
    private Integer kpp;
    /**
     * Адрес
     */
    @JsonProperty
    private String address;
    /**
     * телефон
     */
    @JsonProperty
    private String phone;
    /**
     * Поле isActive
     */
    @JsonProperty
    private Boolean isActive;
}
