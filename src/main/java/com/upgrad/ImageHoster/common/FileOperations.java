package com.upgrad.ImageHoster.common;

import java.io.*;
import java.util.*;

public class FileOperations<T> {

    // creating an object of file operations
    private static final FileOperations fileOperations = new FileOperations();

    // creating a constructor of File operations
    private FileOperations() {
    }

    //creating an instance of file operations
    public static FileOperations getInstance() {
        return fileOperations;
    }

    // Creating a method to read all files by giving them the path to the file storage
    List<T> readAllFiles(String dirPath) {

        synchronized (fileOperations) {

            // Creating a new arraylist to store all files into the list
            List<T> arrayList = new ArrayList<T>();

            //Creating a new object of type File, by specifying the location of the file
            File file = new File(dirPath);

            File[] files = file.listFiles();

            if (files != null) {

                for (File f : files) {

                    try {
                        // Create new FileInputStream object, FileNotFoundException if the agrument File does not exist
                        FileInputStream fileInputStream = new FileInputStream(f);

                        // creates an ObjectInputStream that reads from the specified fileInputStream.
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        // reads an object from the ObjectInputStream.
                        T readObject = (T) objectInputStream.readObject();
                        if (readObject != null) {
                            arrayList.add(readObject);
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        }
    }

    // Creating a method to read two files by giving them the path to the file storage similar to readAllFiles
    List<T> readTwoFiles(String dirPath) {

        synchronized (fileOperations) {

            List<T> arrayList = new ArrayList<T>();

            File file = new File(dirPath);
            File[] files = file.listFiles();

            if (files != null) {

                for (File f : files) {

                    try {
                        FileInputStream fileInputStream = new FileInputStream(f);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        T readObject = (T) objectInputStream.readObject();
                        if (readObject != null) {
                            arrayList.add(readObject);
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Checking if arraylist has 2 or more than two elements
            //If yes then return those 2 elements else return all the elements in the arraylist
            if (arrayList.size() >= 2) {
                ArrayList<T> list = new ArrayList<T>();
                list.addAll(arrayList.subList(0, 2));
                return list;
            } else {
                return arrayList;
            }
        }
    }

    //Writing the image string as a file
    public T writeToFile(final String filePrefix, final T object, final String suffix) {

        synchronized (fileOperations) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePrefix + suffix), true);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            return object;
        }
    }
}