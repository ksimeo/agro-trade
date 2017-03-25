package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.User;

import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
public interface IUserService {

    User addUser(User user);
    User getUserById(int id);
    void deleteUserById(int id);
    User getByName(String name);
    List<User> getAllUsers();
    void deleteAllUsers();
}
