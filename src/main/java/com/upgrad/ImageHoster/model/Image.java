package com.upgrad.ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

// Serializable is used to pass the object instance into a hash function to convert it into bytes format
// and then store it into the database as a row in the table
// The byte stream created is platform independent, so object serialized on one platform can be deserialized on different platform

// You need to declare the model as an entity, so that the system knows that it is a table in the database
@Entity
// with @Table annotation, you specify that image is the name of the table.
@Table(name = "image")
public class Image implements Serializable {

    // image attributes
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(columnDefinition="TEXT")
    private String imageFile; // this is a base64 encoded version of the image

    @Column
    private int numView;

    @Column
    private LocalDate uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    // Constructor
    public Image(Long id, String title, String description, String imageFile, User user) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.imageFile = imageFile;
        this.numView = 0;
        this.user = user;
        this.uploadDate = LocalDate.now();
    }
    public Image(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumView() {
        return numView;
    }

    public void setNumView(int numView) {
        this.numView = numView;
    }
}