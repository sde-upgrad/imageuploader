package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Image;

import java.io.*;
import java.util.*;

// Image manager acts as an interface for all the methods to be implemented through file operations
// Image service implementation will now interface with the image manager, for the required file operations
public class ImageManager {
    private FileOperations fileOperations;

    // creating an instance of File Operations
    public ImageManager() {
        fileOperations = FileOperations.getInstance();
    }

    // getting all the uploaded images
    public List<Image> getAllImages() {

        return (ArrayList<Image>) fileOperations.readAllFiles(Constants.IMAGE_DIR_NAME);
    }

    // getting two images from all the uploaded images
    public List<Image> getTwoImages() {
        // complete the return statement here.
    }

    // saves the image file with the additional name - "image .." and title
    public Image writeToFile(final Image image) {
        return (Image) fileOperations.writeToFile(Constants.POST_FILE_PREFIX, image, String.valueOf(image.getTitle()));
    }

}