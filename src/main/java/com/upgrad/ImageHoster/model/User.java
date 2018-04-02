package com.upgrad.ImageHoster.model;

public class User {

    // User attributes
    private String username;
    private String passwordHash;

    // Constructor
    public User(String username, String passwordHash, String description) {
        this.username = username;
        this.passwordHash = passwordHash;
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
