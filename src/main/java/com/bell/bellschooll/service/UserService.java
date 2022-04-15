package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.request.UpdateUserInDto;
import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.dto.request.UserInSaveDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.dto.response.UserOutDto;
import com.bell.bellschooll.dto.response.UserOutListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Интерфейс сервиса для работы с User
 */
public interface UserService {
    /**
     * Метод для добавления нового пользователя
     *
     * @param userInSaveDto объект с параметрами нового пользователя
     * @return ResponseEntity<SuccessDto>
     */
    @Transactional
    ResponseEntity<SuccessDto> addUser(UserInSaveDto userInSaveDto);

    /**
     * Метод для получения пользователя по id
     *
     * @param id уникальный идентификатор пользователя
     * @return ResponseEntity<UserOutDto>
     */
    ResponseEntity<UserOutDto> getUser(Integer id);

    /**
     * Метод для получения списка пользователей по UserInListDto
     * @param userInListDto объект с параметрами для фильтрации пользователей
     * @return ResponseEntity<List<UserOutListDto>>
     */
    ResponseEntity<List<UserOutListDto>> getListUser(UserInListDto userInListDto);

    @Transactional
    ResponseEntity<SuccessDto> updateUser(UpdateUserInDto updateUserInDto);
}
