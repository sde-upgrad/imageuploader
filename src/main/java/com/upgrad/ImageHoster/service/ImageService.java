package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ImageService{
    // Naming all the services to be implemented in Image Service Imp
    ArrayList<Image> getAll();
    ArrayList<Image> getTwoImages();
    void save(Image image);
    Image getByTitle(String title) ;
    void deleteByTitle(Image image);
    int getNumImages();
    List<Image> getMostNRecentImages(int numImages);
}