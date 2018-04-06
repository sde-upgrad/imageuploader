package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Image;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.*;


// Image manager acts as an interface for all the methods to be implemented through file operations
// Image service implementation will now interface with the image manager, for the required file operations
@SuppressWarnings("unchecked")
public class ImageManager extends SessionManager{


    //main function to implement few services directly from backend
    public static void main(String[] args) {

        // creating an object of ImageManager
        ImageManager imageManager = new ImageManager();

    }


    // creating an instance of File Operations
    public ImageManager() {


    }

    // See the implementation of the getTwoImages() method below and complete the getAllImages() method
    public List<Image> getAllImages() {
        Session session = openSession();
        List<Image> images = session.createCriteria(Image.class).list();
        commitSession(session);

        return images;
    }


    // gets two images from the database
    public List<Image> getTwoImages() {
        Session session = openSession();
        List<Image> images = session.createCriteria(Image.class)
                .setMaxResults(2)
                .list();
        commitSession(session);

        return images;
    }

    // retrieves image by its title from the database by searching all the images
    public Image getImageByTitle(final String title) {
        Session session = openSession();

        try {
            Image image = (Image)session.createCriteria(Image.class)
                    .add(Restrictions.eq("title", title))
                    .uniqueResult();
            commitSession(session);

            return image;

        } catch(HibernateException e) {
            System.out.println("unable to retrieve an image from database by its title");
        }

        return null;
    }

    // saves image details into the database
    public void writeToDatabase(final Image image) {
        Session session = openSession();
        session.save(image);
        commitSession(session);
    }

    // deletes image from the database
    public void deleteImage(final String title) {
        Session session = openSession();
        // to delete an image, you would need to find the image in the database, and then remove it.
        // a general query for a delete command in sql would have been - Delete from table where title = "xyz"
        // when you need to write this query, you need to look in the image table and then search the image by the title and then delete it
        Query query = session.createQuery("Delete from " + Image.class.getName() + " where title=:imageTitle");
        query.setParameter("imageTitle", title);
        query.executeUpdate();
        commitSession(session);
    }



    public void updateImage(final Image updatedImage) {
        Session session = openSession();
        session.update(updatedImage);
        commitSession(session);
    }

}