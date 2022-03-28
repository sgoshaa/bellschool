package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.UserInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    User getUserById(Integer id);
    List<User> getListUser(UserInListDto userInListDto, Office office);
}
