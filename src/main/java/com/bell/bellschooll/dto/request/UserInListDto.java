package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInListDto {
    @NotNull
    private Integer officeId;
    private String firstName;
    @JsonProperty("lastName")
    private String secondName;
    private String middleName;
    private String position;
    private String docCode;
    private String citizenshipCode;
}
