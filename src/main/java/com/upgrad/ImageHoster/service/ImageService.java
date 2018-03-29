package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Image;

import java.util.List;

public interface ImageService{

    List<Image> getAll();
    Image getByTitle(String title);
    void deleteByTitle(Image image);
    void save(Image image);

}