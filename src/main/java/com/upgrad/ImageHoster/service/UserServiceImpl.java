package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.UserManager;
import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserManager userManager;

    public UserServiceImpl() {
        userManager = new UserManager();
    }

    @Override
    public User login(String username, String password){
        //write the return statement to invoke the loginUser method in usermanager
    }

    @Override
    public User getByName(String username) {
        return userManager.getUserByName(username);
    }

    @Override
    public boolean register(User user) {
        //Write the return statement to invoke register method in usermanager
        // Return true if user is not null, else false
    }


}
