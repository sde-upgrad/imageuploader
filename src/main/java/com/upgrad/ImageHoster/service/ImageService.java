package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;

import java.util.List;

public interface ImageService{
    // Naming all the services to be implemented in Image Service Imp
    List<Image> getAll();
    List<Image> getTwoImages();
    void save(Image image);
}