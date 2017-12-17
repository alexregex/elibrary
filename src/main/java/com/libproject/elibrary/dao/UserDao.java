package com.libproject.elibrary.dao;

import com.libproject.elibrary.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findByLogin(String login);

    void saveUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    List<User> findAllUsers();
}
