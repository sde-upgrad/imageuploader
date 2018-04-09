package com.upgrad.ImageHoster.model;


import javax.persistence.*;
import java.io.Serializable;

//Please add annotation to make it a table
 // postgres doesn't allow table named "user" so change the name of the table
public class User implements Serializable {

//Please  add annotations for id to make it a column and a primary key
    private int id;

//Please add annotation to make it a column
    private String username;

    //Please add annotation to make it a column
    private String passwordHash;


    // Constructor
    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public User() {
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
