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

    // map the sign-in in the URL to the sign-in html page in the project
    // the following method displays the main sign-in page
    @RequestMapping("/users/signin")
    public String signin(RegisterNewUser registerNewUser) {
        return "users/signin";
    }


    // map the sign-in, in the URL to the sign-in html page in the project
    // The following method defines the action when you click on the sign in button
    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    public String signinPage(RegisterNewUser registerNewUser) {
        // here you may consider that every login detail entered is valid, so that each login action is successful, even if empty
        boolean validUser = true;
        if (validUser) {
            // after login is successful, redirect the user to the home page
            // In the return statement, path of the redirection message to home.html HTML file in the project is mentioned
            return "redirect:/home";
        }

        return "redirect:/";
    }


    // map the sign-up, in the URL to the signup html file in the project
    // the following method displays the main sign-up page

    // write the mapping request here
    public String signup(RegisterNewUser registerNewUser) {
        // In the return statement write the path of the signup.html HTML file in the project
        return "";
    }

    // map the sign-up, in the URL to the signup html file in the project
    // The following method defines the action when you click on the sign-up button

    // write the mapping request here
    public String signupPage(RegisterNewUser registerNewUser) {
        // here you may consider that every user is valid, so that each registration is successful, even if empty
        boolean validUser = true;

        if (validUser) {
            // after sign up is successful, redirect the user to the login page

            // In the return statement write the path of the redirection to signin.html HTML file in the project
            return "";
        }
        return "redirect:/";
    }
}
