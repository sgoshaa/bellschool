package com.bell.bellschooll.dto.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class OfficeInListDto {
    @NotNull
    private Integer orgId;

    private String name;

    private String phone;

    private Boolean isActive;
}
