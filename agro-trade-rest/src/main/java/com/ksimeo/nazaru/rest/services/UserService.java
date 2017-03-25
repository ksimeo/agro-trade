package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.rest.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {

        return userDao.save(user);
    }

    @Override
    public User getUserById(int id) {

        return userDao.findOne(id);
    }

    @Override
    public void deleteUserById(int id) {

        userDao.delete(id);
    }

    @Override
    public User getByName(String name) {
        try {
            User user = userDao.findByName(name);
            if (user != null) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {

        return (ArrayList<User>)userDao.findAll();
    }

    @Override
    public void deleteAllUsers() {

        userDao.deleteAll();
    }
}