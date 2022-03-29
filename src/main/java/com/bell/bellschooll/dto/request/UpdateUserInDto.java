package com.bell.bellschooll.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdateUserInDto {
    @NotNull
    private Integer id;
    private Integer officeId;
    @NotBlank
    private String firstName;
    private String secondName;
    private String middleName;
    @NotBlank
    private String position;
    private String phone;
    private String docName;
    private String docNumber;
    private LocalDate docDate;
    private Integer citizenshipCode;
    private Boolean isIdentified;
}
