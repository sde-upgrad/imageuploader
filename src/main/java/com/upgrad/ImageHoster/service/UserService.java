package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;


public interface UserService{

    // User methods to be used

    boolean login(String username, String password);
    boolean register(User user);
}
