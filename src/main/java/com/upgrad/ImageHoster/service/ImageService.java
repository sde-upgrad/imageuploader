package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;

import java.util.List;

public interface ImageService{

    List<Image> getTwoImages();
    Image getByTitle(String title);
    Image getByTitleWithJoin(String title);
    void deleteByTitle(Image image);
    void save(Image image);
    void update(Image image);
    // Implement the getAll() method which would display all the images that exist in the database
    List<Image> getAll();
}