package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserInSaveDto {
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer officeId;
    @NotBlank
    private String firstName;
    private String secondName;
    private String middleName;
    @NotBlank
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
