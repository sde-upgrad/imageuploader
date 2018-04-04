package com.upgrad.ImageHoster.model;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

// Serializable is used to pass the object instance into a hash function to convert it into bytes format
// and then store it into the database as a row in the table
// The byte stream created is platform independent, so object serialized on one platform can be deserialized on different platform
public class Image implements Serializable {

    // image attributes
    private Long id;
    private String title;
     // write code for adding "description" variable to give the description of the image
    private String imageFile; // this is a base64 encoded version of the image
     // write code for adding "numView" variable to find the number of views of each image
    private LocalDate uploadDate;

    // Constructor
   // write code for the constructor of above variables and set numView initially as 0

    // Getters and Setters


    //Write code to add the getter and setter of new variables being added
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