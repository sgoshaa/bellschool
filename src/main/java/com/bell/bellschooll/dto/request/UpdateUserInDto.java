package com.bell.bellschooll.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Request DTO для обновления пользователя
 */
@Data
public class UpdateUserInDto {
    /**
     * id пользователя
     */
    @NotNull
    @Range(min = 1, message = "id не может равняться 0")
    private Integer id;
    /**
     * id офиса
     */
    private Integer officeId;
    /**
     * Фамилия пользователя
     */
    @NotBlank
    private String firstName;
    /**
     * Имя пользователя
     */
    private String secondName;
    /**
     * Отчество пользователя
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
    private Integer citizenshipCode;
    /**
     * Поле isIdentified
     */
    private Boolean isIdentified;
}
