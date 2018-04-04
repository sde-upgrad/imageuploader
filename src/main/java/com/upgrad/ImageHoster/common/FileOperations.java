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

        //Here "synchronized" is a java keyword used in multithreading environmentand acts as a lock
        //in the case of file writing and reading (or file IO), it prevents a multithreaded program to write and read a file at the same time
        //i.e. some thread needs to finish writing to the file first and then another thread can read the file
        //but reading and writing cannot (and should not) happen concurrently
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
                        fileInputStream.close();
                        objectInputStream.close();

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

        List<T> arrayList = readAllFiles(dirPath);

            if(arrayList.size()>=2){
                ArrayList<T> list = new ArrayList<T>();
                list.addAll(arrayList.subList(0, 2));
                return list;
            }
            else {
                return arrayList;
            }
            // Add the code to return the top two files in the arraylist
        }


    //Writing the image string as a file
    public T writeToFile(final String filePrefix, final T object, final String suffix) {


        synchronized (fileOperations) {
            try {
                // Changing the append to false because now image can be edited
                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePrefix + suffix), false);
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

    //Reading n recent images from files
    List<T> readRecentFiles(final int numberOfFiles, final String DirLocation) {

        synchronized (fileOperations) {
            Map<Long, File> sortByModificationDate = new TreeMap<Long, File>(Collections.reverseOrder());
            List<T> arrayList = new ArrayList<T>();

            try {
                File file = new File(DirLocation);
                File[] files = file.listFiles();

                if (files != null) {
                    for (File f : files) {
                        sortByModificationDate.put(f.lastModified(), f);
                    }

                    int count = numberOfFiles;
                    for (Long modifiedOn : sortByModificationDate.keySet()) {
                        FileInputStream fileInputStream = new FileInputStream(sortByModificationDate.get(modifiedOn));
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        T readObject = (T) objectInputStream.readObject();
                        if (readObject != null) {
                            arrayList.add(readObject);
                        }
                        count--;
                        fileInputStream.close();
                        objectInputStream.close();
                        if (count <= 0) break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
            return arrayList;
        }
    }

    // reading image from file based upon title
    T readFile(final String filePrefix, final String uniqueId) {

        synchronized (fileOperations) {
            T readObject = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(filePrefix + uniqueId));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                readObject = (T) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Error " + e.getMessage());
            }

            return readObject;
        }
    }

    // Deleting an image from files based upon title
    boolean deleteFile(final String filePrefix, final String uniqueId) {
        synchronized (fileOperations) {
            File file = new File(filePrefix+uniqueId);
            return file.delete();
        }
    }
}