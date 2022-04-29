package com.bell.bellschooll.dto.response;

import lombok.Data;

/**
 * ResponseDTO для вывода списка пользователей
 */
@Data
public class UserOutListDto {
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
}
