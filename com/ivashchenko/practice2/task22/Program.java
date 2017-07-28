package com.ivashchenko.practice2.task22;

/**
 * This class is used to demonstrate searching for regular expression matches in text.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Program {
    public static void main(String[] args) {
        String longText = Reader.askForMultiline("Enter long text: ");
        String regexp = Reader.askForString("Enter your regexp: ");
        RegexHandler.checkMatching(longText, regexp);
    }
}
