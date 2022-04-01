package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class UserInListDto {
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer officeId;
    private String firstName;
    @JsonProperty("lastName")
    private String secondName;
    private String middleName;
    private String position;
    private String docCode;
    private String citizenshipCode;
}
