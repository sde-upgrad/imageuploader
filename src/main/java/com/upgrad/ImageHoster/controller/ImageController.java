package com.upgrad.ImageHoster.controller;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// Annotation
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("/")
    public String home(Model model){

        List<Image> list = imageService.getAll();
        model.addAttribute("images",list);
        return "home";
    }
}
