package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImp implements ImageService {
    //Please implement the services mentioned in the Image Service Interface Here
    //Here Implementation means writing the method specified in the ImageSerice Interface
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

    @Override
    public List<Image> getAll() {
        return images;
        //Complete this method, add the return statement
    }

    @Override
    public List<Image> getTwoImages() {
        List<Image> twoImages = images.subList(0, 2);
        return twoImages;
        //Complete this method, add the return statement
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
        return ;
        //Complete this method, add the return statement
    }
    //Please implement the services mentioned in the Image Service Interface Here
    //Here Implementation means writing the method specified in the ImageSerice Interface
}
