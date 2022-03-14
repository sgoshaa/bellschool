package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrganisationDTO {

    @JsonProperty
    String name;
    @JsonProperty
    Integer inn;
    @JsonProperty
    Boolean isActive;
}
