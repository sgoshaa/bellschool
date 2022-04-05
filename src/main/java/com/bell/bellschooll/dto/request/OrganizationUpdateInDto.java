package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrganizationUpdateInDto {

    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer id;

    @NotBlank(message = "Название - обязательное поле")
    private String name;

    @NotBlank
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
