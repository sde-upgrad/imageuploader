package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // Implementation of methods mentioned in the User Service

    // Method written below verifies that username = "upgrad" and password = "sde"
    // This just a basic check for login, but you will be able to register new user and login based upon that user in the further module
    public boolean login(String username, String password){
        if(username.equals("upgrad")  && password.equals("sde")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    // this is a dummy register method
    // currently you may consider every registration as correct
    public boolean register(User user) {
        return true;
    }

}
