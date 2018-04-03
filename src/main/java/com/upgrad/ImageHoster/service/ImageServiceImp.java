package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.ImageManager;
import com.upgrad.ImageHoster.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImp implements ImageService {

    /*
    // This portion has been commented out as it is no longer needed.
    private List<Image> images = new ArrayList<Image>();
    public ImageServiceImp() {

        // IMPORTANT
        //After you have implemented the constructor and completed the methods in this you may uncomment the files below

        LocalDate localDate = LocalDate.now();

        images.add(new Image(1L,"Image 1","This is Image 1",localDate));
        images.add(new Image(2L,"Image 2","This is Image 2",localDate));
        images.add(new Image(3L,"Image 3","This is Image 3",localDate));
        images.add(new Image(4L,"Image 4","This is Image 4",localDate));

    }
    */
    // creating an instance of Image Manager. Since, file operations are involved, every service will be called via image manager
    private ImageManager imageManager;

    public ImageServiceImp() {
        imageManager = new ImageManager();
    }

    // Gets two images from the uploaded images
    @Override
    public List<Image> getTwoImages(){
        return imageManager.getTwoImages();
    }

    // gets all the images that were uploaded
    @Override
    public List<Image> getAll() {
        return imageManager.getAllImages();
    }

    // writes an image to file
    @Override
    public void save(Image image) {
        imageManager.writeToFile(image);
    }

    // get n most recent images
    @Override
    public List<Image> getMostNRecentImages(int numImages) {

        return imageManager.getMostNRecentImages(numImages);
    }

    //gets the number of images
    @Override
    public int getNumImages() {

        // Write the return statement for getNumImages method.
    }

    // gets the image by its title
    @Override
    public Image getByTitle(String title) {
        // Write the return statement for getByTitle method
    }

    // deletes image by its title
    @Override
    public void deleteByTitle(Image image) {
        imageManager.deleteImage(image.getTitle());
    }

}
