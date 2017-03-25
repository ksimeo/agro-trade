package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.rest.services.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ksimeo on 23.01.2015.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addnewuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User addNewUser(@RequestBody String jsonData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonData, User.class);
        return userService.addUser(user);
    }

    @RequestMapping(value = "deluserbyid/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public void delUserById(@PathVariable int id) {

        userService.deleteUserById(id);
    }

    @RequestMapping(value = "getuserbyid/{id}/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() throws IOException {

        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getuserbyname/{name}/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getByName(@PathVariable String name) {
        try {
            User user = userService.getByName(name);
            if(user != null) {
                return user;
            } else {
                return  null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

//    @RequestMapping(value = "/isexistlogin/{login}/", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public boolean isExistLogin(@PathVariable String login) {
//        User testUser = userService.getByName(login);
//        if (testUser != null) {
//            return true;
//        } else {
//            return false;
//        }
//        try{
//            User testUser = userService.getByName(login);
//            return true;
//        } catch(Exception e) {
//            return false;
//        }

}