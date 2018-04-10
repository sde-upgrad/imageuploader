package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.ProfilePhotoManager;
import com.upgrad.ImageHoster.model.ProfilePhoto;
import org.springframework.stereotype.Service;

//Annotate to establish that it is a service
@Service
public class ProfilePhotoServiceImpl implements ProfilePhotoService  {

    //Create an object of photoManager
    private ProfilePhotoManager profilePhotoManager;

    public ProfilePhotoServiceImpl() {
        profilePhotoManager = new ProfilePhotoManager();
    }

    @Override
    public void save(ProfilePhoto photo){ profilePhotoManager.saveProfilePhoto(photo); }

    @Override
    public void update(ProfilePhoto photo){ profilePhotoManager.updateProfilePhoto(photo); }
}
