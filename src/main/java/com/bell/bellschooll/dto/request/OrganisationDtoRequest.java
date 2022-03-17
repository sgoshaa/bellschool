package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class OrganisationDtoRequest {

    @NotEmpty
    @NotBlank
    @NotNull
    @JsonProperty
    String name;
    @JsonProperty
    Integer inn;
    @JsonProperty
    Boolean isActive;
}
