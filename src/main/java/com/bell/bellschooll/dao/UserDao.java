package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    /**
     * Добавление нового пользователя
     *
     * @param user
     */
    void addUser(User user);

    /**
     * Обновление Пользователя
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * Получение пользователя по id
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * Получение пользователя по фильтру
     *
     * @param userInListDto
     * @param office
     * @return
     */
    List<User> getListUser(UserInListDto userInListDto, Office office);
}
