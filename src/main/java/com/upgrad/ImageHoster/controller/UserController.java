package com.upgrad.ImageHoster.controller;

import com.google.common.hash.Hashing;
import com.upgrad.ImageHoster.model.ProfilePhoto;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.ProfilePhotoService;
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
import java.util.Base64;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // Making object of class ProfilePhotoService to use here
    @Autowired
    private ProfilePhotoService profilePhotoService;

    // mapping the sign-in in the URL to the sign-in html page in the project
    // the following method displays the main sign-in page
    @RequestMapping(value = "/users/signin")
    public String signIn(HttpSession session) {
        if (session.getAttribute("currUser") == null){
            return "users/signin";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    public String signInUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session) {
        User user = userService.login(username, password);

        if (user != null ) {
            session.setAttribute("currUser", user);
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
    public String signUp(HttpSession session) {
        if (session.getAttribute("currUser") == null){
            return "users/signup";

        } else {
            return "redirect:/users/signin";
        }
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String signUpUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {

        ProfilePhoto photo = new ProfilePhoto();
        profilePhotoService.save(photo);

        String passwordHash = hashPassword(password);
        User user = new User(username, passwordHash, photo);
        userService.register(user);

        session.setAttribute("currUser", user);

        return "redirect:/users/signin";
    }


    //Mapping the URL for signout
    @RequestMapping(value = "/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("currUser");
        return "redirect:/";
    }

    // mapping the profile page, in the URL to the profile html file in the project
    // the following method displays the main profile page
    @RequestMapping(value = "/users/edit_profile")
    public String editProfile(HttpSession session, Model model) {
        User currUser = (User)session.getAttribute("currUser");

        // If the current user is null then redirect to home, else open the profile page of user
        if(currUser == null ){
            return "redirect:/home";
        }
        else {
            model.addAttribute("user", currUser);

            return "users/profile.html";
        }
    }


    //Write the mapping for the action on clicking the user profile button on the homepage
    public String editUserProfile(@RequestParam("description") String description,
                                  @RequestParam("file") MultipartFile file,
                                  HttpSession session,
                                  Model model) throws IOException {
        User currUser = (User)session.getAttribute("currUser");

        // update photo data
        ProfilePhoto photo = currUser.getProfilePhoto();
        String photoDataAsBase64 = convertUploadedFileToBase64(file);
        photo.setprofileImageData(photoDataAsBase64);
        profilePhotoService.update(photo);

        // Set the description and profilePhoto of current user and run the update method from userservice

        // Write the return statement to redirect to home
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
