package utils;

import java.util.Scanner;

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

    public static char askForChar(String question) {
        System.out.println(question);
        return scanner.next(".").charAt(0);
    }
}
