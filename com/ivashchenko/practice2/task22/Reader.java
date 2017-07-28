package com.ivashchenko.practice2.task22;

import java.util.Scanner;

/**
 * This class provides static method for reading int and string values.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public static String askForString(String question) {
        System.out.println(question);
        String string = scanner.nextLine();
        return string.isEmpty() ? scanner.nextLine() : string;
    }

    public static String askForMultiline(String question) {
        StringBuilder stringBuilder = new StringBuilder();
        String input;
        System.out.println(question);
        while(!(input = scanner.nextLine()).isEmpty()) {
            stringBuilder.append(input + "\n");
        }
        return stringBuilder.toString();
    }

    public static int askForInt(String question) {
        System.out.println(question);
        return scanner.nextInt();
    }
}
