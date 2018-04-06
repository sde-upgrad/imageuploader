package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Image;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


// Image manager acts as an interface for all the methods to be implemented through file operations
// Image service implementation will now interface with the image manager, for the required file operations
@SuppressWarnings("unchecked")
public class ImageManager {
    private FileOperations fileOperations;

    //main function to implement few services directly from backend
    public static void main(String[] args) {

        // creating an object of ImageManager
        ImageManager imageManager = new ImageManager();

        // creating instance of imageManager to implement deleteImage
        //imageManager.deleteImage("Hi");

        // implementing getNumImages
        //System.out.println(getNumImages());

    }


    // creating an instance of File Operations
    public ImageManager() {
        fileOperations = FileOperations.getInstance();
    }

    // getting all the uploaded images
    public ArrayList<Image> getAllImages() {

        JDBCConnector jdbcConnector = JDBCConnector.getInstant();

        String query = "SELECT * FROM image";
        ResultSet rs = jdbcConnector.executeQuery(query);


        try {

            Long id = null;
            String title = null;
            String description = null;
            String imageFile = null;
            int numView = 0;

            ArrayList<Image> im=  new ArrayList<>();

            while (rs.next()) {
                Image image = new Image(rs.getLong("id"), rs.getString("title"), rs.getString("description"),rs.getString("imagefile"));
                image.setNumView(rs.getInt("numview"));
                im.add(image);
            }


            return im;

        } catch (SQLException e) {
            System.out.print("error retrieving image from database by title");
        }

        return null;
    }

    // getting two images from all the uploaded images
    public ArrayList<Image> getTwoImages() {
        JDBCConnector jdbcConnector = JDBCConnector.getInstant();

        String query = "SELECT * FROM image LIMIT 2";
        ResultSet rs = jdbcConnector.executeQuery(query);


        try {

            Long id = null;
            String title = null;
            String description = null;
            String imageFile = null;
            int numView = 0;

            ArrayList<Image> im=  new ArrayList<>();

            while (rs.next()) {
                Image image = new Image(rs.getLong("id"), rs.getString("title"), rs.getString("description"), rs.getString("imagefile"));
                image.setNumView(rs.getInt("numview"));
                im.add(image);
            }


            return im;

        } catch (SQLException e) {
            System.out.print("error retrieving image from database by title");
        }

        return null;
    }

    // saves the image file with the additional name - "image .." and title
    public Image writeToFile(final Image image) {
        return (Image) fileOperations.writeToFile(Constants.POST_FILE_PREFIX, image, String.valueOf(image.getTitle()));
    }

    // Gives the n most recent images available in the list of images where n< number of images available
    public List<Image> getMostNRecentImages(int numImages) {

        return (ArrayList<Image>) fileOperations.readRecentFiles(numImages, Constants.IMAGE_DIR_NAME);
    }

    // Gives the number of images in the list of images
    public static int getNumImages() {

        // Giving the path, where the images are stored
        File file = new File(Constants.IMAGE_DIR_NAME);
        // adding files as a list
        File[] files = file.listFiles();
        // finding the length of of file's list
        return files.length;
    }

    // Deletes an image of title "t" from the files with a title "t"
    public boolean deleteImage(final String imageTitle) {

        return (boolean) fileOperations.deleteFile(Constants.POST_FILE_PREFIX, imageTitle);
    }

    // Returns an image of title "t" from the files with a title "t"
    public Image getImage(final String prefix) {
        return (Image) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }



    public Image getFromDatabaseByTitle(final String title) {
        JDBCConnector jdbcConnector = JDBCConnector.getInstant();

        String query = "SELECT * FROM images " + title;
        ResultSet rs = jdbcConnector.executeQuery(query);

        try {
            Long id = null;
            String description = null;
            String imageFile = null;
            int numView = 0;

            while (rs.next()) {
                //Retrieve by column name
                id = rs.getLong("id");
                description = rs.getString("description");
                imageFile = rs.getString("imageFile");
                numView = rs.getInt("numView");
            }

            Image imageFromDB = new Image(id, title, description, imageFile);
            imageFromDB.setNumView(numView);
            return imageFromDB;

        } catch (SQLException e) {
            System.out.print("error retrieving image from database by title");
        }

        return null;
    }



    public void writeToDatabase(final Image image) {
        JDBCConnector jdbcConnector = JDBCConnector.getInstant();

        String query = "INSERT INTO image(id, title, description, imageFile, numView, uploadDate) values(" +
                "\'" + image.getId() + "\', " +
                "\'" + image.getTitle() + "\', " +
                "\'" + image.getDescription() + "\', " +
                "\'" + image.getImageFile() + "\', " +
                "\'" + image.getNumView() + "\', " +
                "\'" + image.getUploadDate() +"\')";

        jdbcConnector.execute(query);
    }


}