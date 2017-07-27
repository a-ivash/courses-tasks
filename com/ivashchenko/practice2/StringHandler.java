package com.ivashchenko.practice2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to demonstrate work with Pattern and Match classes.
 * It is also used to read user text and replace every ending of the word matching pattern
 * with replacement string.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class StringHandler {
    Scanner scanner = new Scanner(System.in);
    public void handle() {
        String inputText = readLongInput();
        String wordEnding = askForInput("Enter word ending: ");
        String replaceWith = askForInput("Enter string to replace it with: ");
        System.out.println("Your text with replacements: ");
        String manualReplacement = manualReplaceWordEndWith(inputText, wordEnding, replaceWith);
        String automaticReplacement = automaticReplaceWordEndWith(inputText, wordEnding, replaceWith);
        System.out.println(manualReplacement);


        System.out.print("Texts are ");
        System.out.println((manualReplacement.equals(automaticReplacement) ? "" : "not ") + "equal");
    }

    private String readLongInput() {
        System.out.println("Enter very long text: ");
        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = scanner.nextLine()).isEmpty()) {
            sb.append(input + "\n");
        }
        return sb.toString();
    }

    private String askForInput(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    private String automaticReplaceWordEndWith(String text, String wordEnd, String replaceWith) {
        StringBuilder sbAutomatic = new StringBuilder(text.replaceAll(wordEnd + "\\b", replaceWith));
        return sbAutomatic.toString();
    }

    private String manualReplaceWordEndWith(String text, String wordEnd, String replaceWith) {
        StringBuilder sbManual = new StringBuilder(text);
        Pattern patternEnding = Pattern.compile(wordEnd + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcherEnding = patternEnding.matcher(text);
        int shift = 0;

        while (matcherEnding.find()) {
            sbManual.replace(shift + matcherEnding.start(), shift + matcherEnding.end(), replaceWith);
            int oldLength = matcherEnding.end() - matcherEnding.start();
            // After replacing into StringBuilder instance all indices are shifted by
            // replaceWith.length() - oldLength positions.
            shift += replaceWith.length() - oldLength;
        }

        return sbManual.toString();
    }
}
