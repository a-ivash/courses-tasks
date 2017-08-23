package com.ivashchenko.practice5.task55;

import com.ivashchenko.practice2.task22.Reader;

import java.io.File;
import java.io.FileNotFoundException;

/** This class handles user's input for a valid location of starting directory.*/
public class PathHandler {
    public static File askForFile(String question) {
        File file;
        do {
            String path = Reader.askForString(question);
            file = new File(path);
        } while (!file.exists() || !file.isDirectory());
        return file;
    }


    public static String askForDirectory(String question) {
        File file = askForFile(question);
        return file.getAbsolutePath();
    }
}
