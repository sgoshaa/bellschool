package com.bell.bellschooll.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrganizationSaveInDto {

    @NotNull
    @NotBlank(message = "Название - обязательное поле")
    private String name;

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    private Integer inn;

    @NotNull
    private Integer kpp;
    @NotNull
    private String address;
    private String phone;
    private Boolean isActive;
}
