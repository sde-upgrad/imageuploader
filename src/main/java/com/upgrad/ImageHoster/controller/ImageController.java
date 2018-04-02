package com.upgrad.ImageHoster.controller;

import com.upgrad.ImageHoster.forms.RegisterNewUser;
import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// Annotation
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;


    // mapping the default/landing page when website url is called, here default url is http://localhost/80xx
    // the landing page is landing.html
    @RequestMapping("/")
    public String landingpage(Model model) {
        List<Image> list = imageService.getTwoImages();
        model.addAttribute("images", list);
        return "landingpage";
    }

    // mapping the home in the URL to the home html page in the project
    // the following method displays the home page
    @RequestMapping("/home")
    public String home(Model model) {

        List<Image> list = imageService.getAll();
        model.addAttribute("images", list);

        return "home";
    }

    // mapping the upload in the URL to the upload html page in the project
    // the following method displays the upload page
    @RequestMapping("/upload")
    public String upload(Model model) {

        return "upload";
    }

    // mapping the upload in the URL to the upload html page in the project
    // The following method defines the action when you click on the upload button
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPage(RegisterNewUser registerNewUser) {
        // after upload is successful, redirect the user to the home page
        return "redirect:/home";
    }
}
