package com.upgrad.ImageHoster.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    // Write the annotation for many to many between images and tags where they are mapped by tags
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Image> images;


    //Constructor
    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }


    // Getter and Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
