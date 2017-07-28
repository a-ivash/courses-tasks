package com.ivashchenko.practice2.task21;

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
        String appendString = askForInput("Enter string to append it with: ");
        System.out.println("Your text with changes: ");
        String manualInserting = manualInsertAfterWordEndWith(inputText, wordEnding, appendString);
        String automaticInserting = automaticInsertAfterWordEndWith(inputText, wordEnding, appendString);
        System.out.println(manualInserting);

        System.out.print("Texts are ");
        System.out.println((manualInserting.equals(automaticInserting) ? "" : "not ") + "equal");
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

    private String automaticInsertAfterWordEndWith(String text, String wordEnd, String appendString) {
        StringBuilder sbAutomatic = new StringBuilder(text.replaceAll(wordEnd + "\\b", wordEnd + appendString));
        return sbAutomatic.toString();
    }

    private String manualInsertAfterWordEndWith(String text, String wordEnd, String appendString) {
        StringBuilder sbManual = new StringBuilder(text);
        Pattern patternEnding = Pattern.compile(wordEnd + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcherEnding = patternEnding.matcher(text);
        int shift = 0;

        while (matcherEnding.find()) {
            sbManual.insert(shift + matcherEnding.end(), appendString);
            // After inserting into StringBuilder instance all indices are shifted by replaceWith.length()
            shift += appendString.length();
        }

        return sbManual.toString();
    }
}
