package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // Implementation of methods mentioned in the User Service

    // this is a dummy login method
    // currently you may consider every login detail entered is correct
    public boolean login(String username, String password){
        return true;
    }

    @Override
    // this is a dummy register method
    // currently you may consider every registration as correct
    public boolean register(User user) {
        return true;
    }

}
