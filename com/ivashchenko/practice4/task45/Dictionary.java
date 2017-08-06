package com.ivashchenko.practice4.task45;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to translate texts from english into russian using words
 * from HashMap instance. Words are stored in file.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Dictionary {
    private static Map<String, String> dictionary;
    static{
        dictionary = new HashMap<>();
        readFromFile();
    }

    private static void readFromFile() {
        try {

            BufferedReader bufferedIS = new BufferedReader(new FileReader("words.txt"));
            String input;
            while ((input = bufferedIS.readLine()) != null) {
                addWord(input.split(", ")[0], input.split(", ")[1]);
            }
        } catch (IOException e) {

        }
    }

    public static void addWord(String english, String russian){
        dictionary.put(english, russian);
    }

    public static String translate(String longText){
        longText = longText.replaceAll("(\\.|,|!|\\?|:)", "");
        System.out.println(longText);

        String[] words = longText.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word: words) {
            // if word not in dictionary put old word
            sb.append(dictionary.getOrDefault(word, word) + " ");
        }
        return sb.toString();
    }
}
