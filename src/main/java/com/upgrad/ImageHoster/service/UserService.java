package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.User;

public interface UserService{
    User login(String username, String password);
    User getByName(String username);
    boolean register(User user);
}
