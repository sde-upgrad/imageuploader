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


    // map the default/landing page when website url is called, here default url is http://localhost/80xx
    // the landing page is landing.html
    @RequestMapping("/")
    public String landingpage(Model model) {
        List<Image> list = imageService.getTwoImages();
        model.addAttribute("images", list);
        // Line below indicates the name of the html page to be returned
        return "landingpage";
    }

    // map the home in the URL to the home html page in the project
    // the following method displays the home page

    // write the mapping request here
    public String home(Model model) {

        List<Image> list = imageService.getAll();
        model.addAttribute("images", list);

        // In the return statement write the path of the home.html HTML file in the project
        return "";
    }

    // mapping the upload in the URL to the upload html page in the project
    // the following method displays the upload page

    // write the mapping request here
    public String upload(Model model) {
        // In the return statement write the path of the upload.html HTML file in the project
        return "";
    }

    // mapping the upload in the URL to the upload html page in the project
    // The following method defines the action when you click on the upload button
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPage(RegisterNewUser registerNewUser) {
        // after upload is successful, redirect the user to the home page
        return "redirect:/home";
    }
}
