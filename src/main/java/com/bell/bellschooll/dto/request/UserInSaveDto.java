package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserInSaveDto {
    @NotNull
    private Integer officeId;
    @NotNull
    @NotBlank
    @Min(2)
    private String firstName;
    private String secondName;
    private String middleName;
    @NotNull
    @NotBlank
    @Min(2)
    private String position;
    private String phone;
    private String docCode;
    private String docName;
    private String docNumber;
    private LocalDate docDate;
//    @JsonProperty("citizenshipCode")
//    private String country;
    private Boolean isIdentified;
}
