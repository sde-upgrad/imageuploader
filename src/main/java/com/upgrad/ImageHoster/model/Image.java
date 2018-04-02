package com.upgrad.ImageHoster.model;

import java.time.LocalDate;
import java.io.Serializable;


public class Image implements Serializable {

    // image attributes
    private Long id;
    private String title;
    private String imageFile; // this is a base64 encoded version of the image
    private LocalDate uploadDate;

    // Constructor
    public Image(Long id, String title, String imageFile) {
        this.id = id;
        this.title = title;
        this.imageFile = imageFile;
        this.uploadDate = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

}