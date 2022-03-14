package com.bell.bellschooll.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationOutDto {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String fullName;
    @JsonProperty
    private Integer inn;
    @JsonProperty
    private Integer kpp;
    @JsonProperty
    private String address;
    @JsonProperty
    private String phone;
    @JsonProperty
    private Boolean isActive;
}
