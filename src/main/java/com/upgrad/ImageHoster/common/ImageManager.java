package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Image;

import java.io.*;
import java.util.*;

// Image manager acts as an interface for all the methods to be implemented through file operations
// Image service implementation will now interface with the image manager, for the required file operations
@SuppressWarnings("unchecked")
public class ImageManager {
    private FileOperations fileOperations;

    //main function to implement few services directly from backend
    public static void main(String[] args) {

        // creating an object of ImageManager
        ImageManager imageManager = new ImageManager();

        // creating instance of imageManager to implement deleteImage
        //imageManager.deleteImage("Hi");

        // implementing getNumImages
        System.out.println(getNumImages());

    }


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
        return (ArrayList<Image>) fileOperations.readTwoFiles(Constants.IMAGE_DIR_NAME);
    }

    // saves the image file with the additional name - "image .." and title
    public Image writeToFile(final Image image) {
        return (Image) fileOperations.writeToFile(Constants.POST_FILE_PREFIX, image, String.valueOf(image.getTitle()));
    }

    // Gives the n most recent images available in the list of images where n< number of images available
    public List<Image> getMostNRecentImages(int numImages) {

        return (ArrayList<Image>) fileOperations.readRecentFiles(numImages, Constants.IMAGE_DIR_NAME);
    }

    // Gives the number of images in the list of images
    public static int getNumImages() {

        // Giving the path, where the images are stored
        File file = new File(Constants.IMAGE_DIR_NAME);
        // adding files as a list
        File[] files = file.listFiles();
        // finding the length of of file's list
        return files.length;
    }

    // Deletes an image of title "t" from the files with a title "t"
    public boolean deleteImage(final String imageTitle) {

        return (boolean) fileOperations.deleteFile(Constants.POST_FILE_PREFIX, imageTitle);
    }

    // Returns an image of title "t" from the files with a title "t"
    public Image getImage(final String prefix) {
        return (Image) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }

}