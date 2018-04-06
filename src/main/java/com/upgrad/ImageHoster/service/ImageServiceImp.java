package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.ImageManager;
import com.upgrad.ImageHoster.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImp implements ImageService {

    private ImageManager imageManager;

    public ImageServiceImp() {
        imageManager = new ImageManager();
    }

    //***** Implement the getAll() method here

    @Override
    public List<Image> getTwoImages() { return imageManager.getTwoImages(); }

    @Override
    public Image getByTitle(String title) {
        return imageManager.getImageByTitle(title);
    }
    @Override
    public void deleteByTitle(Image image) {
        imageManager.deleteImage(image.getTitle());
    }

    @Override
    public void save(Image image) { imageManager.writeToDatabase(image); }

    @Override
    public void update(Image newImage) { imageManager.updateImage(newImage); }
}
