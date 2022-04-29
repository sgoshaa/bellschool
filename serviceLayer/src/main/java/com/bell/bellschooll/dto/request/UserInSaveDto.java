package com.bell.bellschooll.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Request DTO для сохранения нового пользователя
 */
@Data
public class UserInSaveDto {
    /**
     * ia офиса
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer officeId;
    /**
     * Фамилия
     */
    @NotBlank
    private String firstName;
    /**
     * Имя
     */
    private String secondName;
    /**
     * Отчество
     */
    private String middleName;
    /**
     * Должность
     */
    @NotBlank
    private String position;
    /**
     * Телефон
     */
    private String phone;
    /**
     * Код документа
     */
    private String docCode;
    /**
     * Название документа
     */
    private String docName;
    /**
     * Номер документа
     */
    private String docNumber;
    /**
     * Дата документа
     */
    private LocalDate docDate;
    /**
     * Код страны
     */
    @JsonProperty("citizenshipCode")
    @NotNull
    private String countryCode;
    /**
     * Поле isIdentified
     */
    private Boolean isIdentified;
}
