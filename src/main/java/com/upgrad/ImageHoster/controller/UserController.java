package com.upgrad.ImageHoster.controller;

import com.google.common.hash.Hashing;
import com.upgrad.ImageHoster.forms.RegisterNewUser;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // mapping the sign-in in the URL to the sign-in html page in the project
    // the following method displays the main sign-in page
    @RequestMapping(value = "/users/signin")
    public String signIn(HttpSession session) {

            return "users/signin";
    }

    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    public String signInUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session) {
        User user = userService.login(username, password);

        if (user != null ) {
            return "redirect:/home";
        } else {
            String errors = "incorrect username or password";
            model.addAttribute("error", errors);

            return "redirect:/users/signin";
        }
    }



    // mapping the sign-up, in the URL to the signup html file in the project
    // the following method displays the main sign-up page

    @RequestMapping(value = "/users/signup")
    public String signUp() {
        return "users/signup";
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String signUpUser(@RequestParam("username") String username,
                             @RequestParam("password") String password) {

        String passwordHash = hashPassword(password);
        User user = new User(username, passwordHash);
        userService.register(user);

        return "redirect:/users/signin";
    }

    // This is used to convert the image file into base 64 format
    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    // This is used to convert password to a hashpassword
    private String hashPassword(String password) {
        String passwordHash = Hashing.sha256()
                .hashString(password)
                .toString();

        return passwordHash;
    }

}
