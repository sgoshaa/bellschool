package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.User;

/**
 * DAO для работы с User
 */
public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    User getUserById(Integer id);
}
