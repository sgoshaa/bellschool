package com.bell.bellschooll.dto.response;


import lombok.Data;

@Data
public class OfficeOutDto {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
}
