package com.bell.bellschooll.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Response DTO для вывода списка организаций
 */
@Data
public class OrganizationListOut {
    /**
     * id
     */
    @JsonProperty
    private Integer id;
    /**
     * Название
     */
    @JsonProperty
    private String name;
    /**
     * Поле isActive
     */
    @JsonProperty
    private Boolean isActive;

}
