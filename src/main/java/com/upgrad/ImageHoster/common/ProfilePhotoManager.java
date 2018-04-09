package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.ProfilePhoto;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class ProfilePhotoManager extends SessionManager {
    public void saveProfilePhoto(final ProfilePhoto photo) {
        Session session = openSession();
        session.save(photo);
        commitSession(session);
        System.out.println("saved photo");
    }

    public void updateProfilePhoto(final ProfilePhoto updatedphoto) {
        Session session = openSession();
        session.update(updatedphoto);
        commitSession(session);
    }
}
