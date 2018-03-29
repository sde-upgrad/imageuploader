package com.upgrad.ImageHoster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Annotation
@Controller
public class ImageController {


    @RequestMapping("/")
    public String home(Model model){
        return "home";
    }
}
