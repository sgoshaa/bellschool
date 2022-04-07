package com.bell.bellschooll.dto.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * ResponseDTO для пользователя
 */
@Data
public class UserOutDto {
    /**
     * id
     */
    private Integer id;
    /**
     * Фамилия
     */
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
     * Гражданство
     */
    private String citizenshipName;
    /**
     * Код страны
     */
    private Integer citizenshipCode;
    /**
     * Поле isIdentified
     */
    private Boolean isIdentified;
}
