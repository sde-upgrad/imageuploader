package com.upgrad.ImageHoster.model;

import java.time.LocalDate;
import java.io.Serializable;


public class Image implements Serializable {
    private Long id;
    private String title;
    private String imageFile; // this is a base64 encoded version of the image
    private LocalDate uploadDate;


// Implement Getter-Setter and Constructor
}