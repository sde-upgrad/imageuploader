package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // Implementation of methods mentioned in the User Service

    // Method written below verifies that username = "upgrad" and password = "sde"
    public boolean login(String username, String password){
        //write your code here for verification
    }

    @Override
    // this is a dummy register method
    // currently you may consider every registration as correct
    public boolean register(User user) {
        return true;
    }

}
