package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImp implements ImageService {
    private List<Image> images = new ArrayList<Image>();
    public ImageServiceImp() {

        // IMPORTANT
        //After you have implemented the constructor and completed the methods in this you may uncomment the files below

        LocalDate localDate = LocalDate.now();

        images.add(new Image(1L,"Image 1","This is Image 1",localDate));
        images.add(new Image(2L,"Image 2","This is Image 2",localDate));
        images.add(new Image(3L,"Image 3","This is Image 3",localDate));

    }

    @Override
    public List<Image> getAll() {
        //Complete this method, add the return statement
        return images;
    }

    @Override
    public Image getByTitle(String title) {
        return null;
        //Complete this method, add the return statement
    }

    @Override
    public void deleteByTitle(Image image) {
        return;
        //Complete this method, add the return statement
    }

    @Override
    public void save(Image image) {
        return;
        //Complete this method, add the return statement
    }
    //Please implement the services mentioned in the Image Service Interface Here
    //Here Implementation means writing the method specified in the ImageSerice Interface
}
