package com.bell.bellschooll.dto.request;


import lombok.Data;

@Data
public class OrganizationSaveInDto {
    private String name;
    private String fullName;
    private Integer inn;
    private Integer kpp;
    private String address;
    private String phone;
    private Boolean isActive;
}
