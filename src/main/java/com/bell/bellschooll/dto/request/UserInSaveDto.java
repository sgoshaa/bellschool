package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


//officeId”:””, //обязательный параметр
//        “firstName”:””, //обязательный параметр
//        “secondName”:””,
//        “middleName”:””,
//        “position”:”” //обязательный параметр


@Data
public class UserInSaveDto {
    @NotNull
    private Integer officeId;
    @NotEmpty
    @NotBlank
//    @Min(2)
    private String firstName;
    private String secondName;
    private String middleName;
    @NotEmpty
    @NotBlank
//    @Min(4)
    private String position;
    private String phone;
    private String docCode;
    private String docName;
    private String docNumber;
    private LocalDate docDate;

    @JsonProperty("citizenshipCode")
    @NotNull
    private Integer countryCode;
    private Boolean isIdentified;
}
