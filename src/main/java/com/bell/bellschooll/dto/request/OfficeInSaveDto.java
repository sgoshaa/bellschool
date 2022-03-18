package com.bell.bellschooll.dto.request;


import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class OfficeInSaveDto {
    @NotNull
    private Integer orgId;
    private String name;
    private String phone;
    private String address;
    private Boolean isActive;
}
