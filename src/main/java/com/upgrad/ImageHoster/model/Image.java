package com.upgrad.ImageHoster.model;

import java.time.LocalDate;
import java.io.Serializable;

//
public class Image implements Serializable {
    private Long id;
    private String title;
    private String imageFile; // this is a base64 encoded version of the image
    private LocalDate uploadDate;

    public Image(Long id, String title, String imageFile, LocalDate uploadDate) {
        this.id = id;
        this.title = title;
        this.imageFile = imageFile;
        this.uploadDate = LocalDate.now();
    }

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

    public String getUploadDate() {
        return uploadDate.toString();
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
// Implement Getter-Setter and Constructor
}