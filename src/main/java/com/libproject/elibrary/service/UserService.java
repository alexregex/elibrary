package com.libproject.elibrary.service;

import com.libproject.elibrary.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findByLogin(String login);

    void saveUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    List<User> findAllUsers();

    boolean isUserLoginUnique(Integer id, String login);
}
