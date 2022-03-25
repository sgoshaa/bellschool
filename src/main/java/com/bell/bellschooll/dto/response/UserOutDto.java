package com.bell.bellschooll.dto.response;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserOutDto {
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private String docName;
    private String docNumber;
    private LocalDate docDate;
    private String citizenshipName;
    private Integer citizenshipCode;
    private Boolean isIdentified;
}
