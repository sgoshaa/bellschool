package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Request DTO для фильтрации пользователя
 */
@Data
public class UserInListDto {
    /**
     * id офиса
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer officeId;
    /**
     * Фамилия
     */
    private String firstName;
    /**
     * Имя
     */
    @JsonProperty("lastName")
    private String secondName;
    /**
     * Отчество
     */
    private String middleName;
    /**
     * Должность
     */
    private String position;
    /**
     * Код документа
     */
    private String docCode;
    /**
     * Код страны
     */
    private String citizenshipCode;
}
