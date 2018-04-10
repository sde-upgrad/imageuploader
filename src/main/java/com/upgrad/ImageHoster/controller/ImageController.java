package com.upgrad.ImageHoster.controller;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.model.ProfilePhoto;
import com.upgrad.ImageHoster.model.Tag;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.ImageService;
import com.upgrad.ImageHoster.service.TagService;
import com.upgrad.ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;


@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;


    @Autowired
    private TagService tagService;

    // mapping the default/landing page when website url is called, here default url is http://localhost/80xx
    // the landing page is landing.html
    @RequestMapping("/")
    public String landingPage(Model model) {
        //Implement getAll() method instead of getTwoImages() method here
        List<Image> list = imageService.getTwoImages();
        model.addAttribute("images", list);
        return "landingpage";
    }

    // mapping the home in the URL to the home html page in the project
    // the following method displays the home page
    @RequestMapping("/home")
    public String home(HttpSession session,Model model) {
    //Getting the session details
        List<Image> image =imageService.getAll();
        model.addAttribute("images", image);

        // Setting the current User as currUser by taking it from session
        User currUser = (User)session.getAttribute("currUser");
        ProfilePhoto photo = currUser.getProfilePhoto();
        model.addAttribute("profilephoto",photo);
        return "home";
    }

    // mapping the upload in the URL to the upload html page in the project
    // the following method displays the upload page
    @RequestMapping("/upload")
    public String upload(HttpSession session) {
        // Get details about the current user
        User currUser = (User) session.getAttribute("currUser");

        // If the current user is null then redirect back to homepage, else redirect to upload page
        // We are checking it to make sure a user is able to upload an image if and only if they are logged in and the session has been started
        if(currUser == null ){
            return "redirect:/";
        }
        else {
            return "/upload";
        }
    }


    // mapping the upload in the URL to the upload html page in the project
    // The following method defines the action when you click on the upload button
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    // defining the upload
    public String uploadFile(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("tags") String tags,
                             HttpSession session) throws IOException {

        User currUser = (User) session.getAttribute("currUser");

        if(currUser == null ){
            return "redirect:/";
        }

        else {
            List<Tag> imageTags = findOrCreateTags(tags);
            // creating an id of the image
            Long imageId = createId();
            // uploading the image and converting it to a base64 encoded version of the image
            String uploadedImageData = convertUploadedFileToBase64(file);
            Image newImage = new Image(imageId, title, description, uploadedImageData, currUser, imageTags);
            // storing the new image
            imageService.save(newImage);
            // after upload is successful, redirect the user to the home page
            return "redirect:/home";
        }
    }


    //mapping the image in the URL to the image html page in the project
    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable String title, Model model) {
        // Finding the image based upon its title by using joins because we need only those instances which contain the tags as we have to pass the tags to the view
        Image image = imageService.getByTitleWithJoin(title);
        // Incrementing the numview by 1 if this page is open
         image.setNumView(image.getNumView() + 1);
        // Saving the image details to update the image
        imageService.update(image);
        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());

        return "images/image";
    }

    // The following method defines the action when you click on the delete button
    @RequestMapping(value = "/images/{title}/delete")
    public String deleteImage(@PathVariable String title, Model model) {
        // Finding the image based upon its title
        Image image = imageService.getByTitle(title);
        // deleting the image by its title
        imageService.deleteByTitle(image);

        // redirecting to home once the action is complete
        return "redirect:/home";
    }

    //mapping the edit image in the URL to the edit html page in the project
    @RequestMapping("/images/{title}/edit")
    public String editImage(@PathVariable String title, Model model) {
        // Finding the image based upon its title by using joins because we need only those instances which contain the tags as we have to pass the tags to the view
        Image image = imageService.getByTitleWithJoin(title);

        // Get the tag list and convert the list into a single string using "convertTagsToString" method defined below,
        // then add it as an attribute and pass it onto the view as the name "tags"

        model.addAttribute("image", image);
        return "images/edit";
    }

    // The following method defines the action when you click on the edit button
    @RequestMapping(value = "/editImage", method = RequestMethod.POST)
    // @RequestParam is used to receive data from the html file
    public String edit(@RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("file") MultipartFile file,
                       @RequestParam("tags") String tags) throws IOException {
        Image image = imageService.getByTitle(title);
        //Converting the new image received to base 64
        List<Tag> imageTags = findOrCreateTags(tags);
        String updatedImageData = convertUploadedFileToBase64(file);

        // if the new image is empty then it means image is not updated, so setting the image to the previous image itself
        if(updatedImageData.isEmpty())
            image.setImageFile(image.getImageFile());
            // else setting the new image to the updated image
        else {image.setImageFile(updatedImageData);}

        // setting the image tags
        image.setTags(imageTags);

        // setting the image description to the new description
        image.setDescription(description);

        // saving the changes done to the image to update it
        imageService.update(image);

        // redirecting back to the image page
        return "redirect:/images/" + title;
    }


    // create a unique id for the uploaded image file
    private Long createId() {
        return System.currentTimeMillis() % 1000;
    }

    // converting to a base64 encoded version of the image
    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    private List<Tag> findOrCreateTags(String tagNames) {
        StringTokenizer st = new StringTokenizer(tagNames, ",");
        List<Tag> tags = new ArrayList<Tag>();

        while(st.hasMoreTokens()) {
            String tagName = st.nextToken().trim();
            Tag tag = tagService.getByName(tagName);

            if(tag == null) {
                Tag newTag = new Tag(tagName);
                tag = tagService.createTag(newTag);
            }

            tags.add(tag);
        }

        return tags;
    }
    private String convertTagsToString (List<Tag> tags) {
        StringBuilder tagString = new StringBuilder();

        for(int i = 0; i <= tags.size() - 2; i++) {
            tagString.append(tags.get(i).getName()).append(", ");
        }

        Tag lastTag = tags.get(tags.size() - 1);
        tagString.append(lastTag.getName());

        return tagString.toString();
    }

}

