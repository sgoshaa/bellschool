package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Request DTO организации
 */
@Data
public class OrganisationDtoRequest {
    /**
     * Название организации
     */
    @NotBlank
    @JsonProperty
    String name;
    /**
     * ИНН
     */
    @JsonProperty
    Integer inn;
    /**
     * Поле isActive
     */
    @JsonProperty
    Boolean isActive;
}
