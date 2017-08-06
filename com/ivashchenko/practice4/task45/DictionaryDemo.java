package com.ivashchenko.practice4.task45;

import com.ivashchenko.practice2.task22.Reader;

/**
 * This class is used to demonstrate work with Dictionary class.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class DictionaryDemo {
    public static void main(String[] args) {
        String translateText = Reader.askForString("Enter text to translate: ");
        System.out.println(Dictionary.translate(translateText));
    }
}
