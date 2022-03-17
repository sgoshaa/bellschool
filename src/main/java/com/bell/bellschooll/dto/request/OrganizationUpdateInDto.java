package com.bell.bellschooll.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrganizationUpdateInDto {

    @NotNull
    private Integer id;

    @NotEmpty(message = "Название - обязательное поле")
    @NotBlank(message = "Название - обязательное поле")
    @NotNull
    private String name;

    @NotEmpty
    @NotBlank
    @NotNull
    private String fullName;

    @NotNull
    private Integer inn;

    @NotNull
    private Integer kpp;

    @NotEmpty
    @NotBlank
    @NotNull
    private String address;

    private String phone;
    @NotNull(message = "по умолчанию true")
    private Boolean isActive;
}
