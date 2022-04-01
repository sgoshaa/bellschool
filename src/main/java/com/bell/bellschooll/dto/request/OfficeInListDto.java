package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class OfficeInListDto {
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer orgId;

    private String name;

    private String phone;

    private Boolean isActive;
}
