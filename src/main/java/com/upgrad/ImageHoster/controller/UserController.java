package com.upgrad.ImageHoster.controller;

import com.google.common.hash.Hashing;
import com.upgrad.ImageHoster.forms.RegisterNewUser;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.StandardCharsets;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // mapping the sign-in in the URL to the sign-in html page in the project
    // the following method displays the main sign-in page
    @RequestMapping("/users/signin")
    public String signin(RegisterNewUser registerNewUser) {
        return "users/signin";
    }


    // mapping the sign-in, in the URL to the sign-in html page in the project
    // The following method defines the action when you click on the sign in button
    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    public String signinPage(RegisterNewUser registerNewUser) {
        // here you may consider that every login detail entered is valid, so that each login action is successful, even if empty
        boolean validUser = true;
        if (validUser) {
            // after login is successful, redirect the user to the home page
            return "redirect:/home";
        }
        return "redirect:/";
    }


    // mapping the sign-up, in the URL to the signup html file in the project
    // the following method displays the main sign-up page
    @RequestMapping("/users/signup")
    public String signup(RegisterNewUser registerNewUser) {
        return "users/signup";
    }

    // mapping the sign-up, in the URL to the signup html file in the project
    // The following method defines the action when you click on the sign-up button
    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String signupPage(RegisterNewUser registerNewUser) {
        // here you may consider that every user is valid, so that each registration is successful, even if empty
        boolean validUser = true;

        if (validUser) {
            // after sign up is successful, redirect the user to the login page
            return "redirect:/users/signin";
        }
        return "redirect:/";
    }
}
