package com.upgrad.ImageHoster.controller;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;


    // mapping the default/landing page when website url is called, here default url is http://localhost/80xx
    // the landing page is landing.html
    @RequestMapping("/")
    public String landingPage(Model model) {
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

        return "/upload";
    }

    // mapping the upload in the URL to the upload html page in the project
    // The following method defines the action when you click on the upload button
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    // defining the upload
    public String uploadFile(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("file") MultipartFile file) throws IOException {
        // creating an ide of the image
        Long imageId = createId();
        // uploading the image and converting it to a base64 encoded version of the image
        String uploadedImageData = convertUploadedFileToBase64(file);
        Image newImage = new Image(imageId, title,description, uploadedImageData);
        // storing the new image
        imageService.save(newImage);
        // after upload is successful, redirect the user to the home page
        return "redirect:/home";
    }


    //mapping the image in the URL to the image html page in the project
    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable String title, Model model) {
        // Finding the image based upon its title
        Image image = imageService.getByTitle(title);
        // Incrementing the numview by 1 if this page is open
        image.setNumView(image.getNumView() + 1);
        // Saving the image details to update the image
        imageService.save(image);
        model.addAttribute("image", image);

        return "images/image";
    }




    // create a unique id for the uploaded image file
    private Long createId() {
        return System.currentTimeMillis() % 1000;
    }

    // converting to a base64 encoded version of the image
    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
}