package com.ksimeo.nazaru.rest.dao;

import com.ksimeo.nazaru.core.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE login=:login", nativeQuery=true)
    public User findByName(@Param("login") String login);
}