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
     * @param user объект пользователя
     */
    void addUser(User user);

    /**
     * Обновление Пользователя
     *
     * @param user объект пользователя
     */
    void updateUser(User user);

    /**
     * Получение пользователя по id
     *
     * @param id уникалльный идентификатор пользователя
     * @return User
     */
    User getUserById(Integer id);

    /**
     * Получение пользователя по фильтру
     *
     * @param userInListDto список параметров по которым фильтруем пользователя
     * @param office офис , в котором работает пользователь
     * @return List пользователей
     */
    List<User> getListUser(UserInListDto userInListDto, Office office);
}
