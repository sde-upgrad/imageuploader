package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.TagManager;
import com.upgrad.ImageHoster.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagManager tagManager;

    public TagServiceImpl() {
        tagManager = new TagManager();
    }

    public List<Tag> getAll() {
        //add the return statement to call the appropriate function for tagManager

    }

    public Tag getByName(String title) {
        //add the return statement to call the appropriate function for tagManager

    }

    public Tag createTag(Tag tag) {
        //add the return statement to call the appropriate function for tagManager
        
    }
}
