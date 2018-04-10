package com.upgrad.ImageHoster.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user1") // postgres doesn't allow table named "user"
public class User implements Serializable {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String passwordHash;


    @Column()
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    private ProfilePhoto profilePhoto;

    // Write the annotation to map user with image using one to many relation and it should be mapped by user
    private List<Image> images = new ArrayList<Image>();

    // Constructor
    public User() { }

    public User(String username, String passwordHash, ProfilePhoto photo) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.profilePhoto = photo;
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

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ProfilePhoto getProfilePhoto() { return profilePhoto; }

    public void setProfilePhoto(ProfilePhoto profilePhoto) { this.profilePhoto = profilePhoto; }

    public List<Image> getImages() { return images; }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
