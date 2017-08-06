package com.ivashchenko.practice4.task42;

import com.ivashchenko.practice2.task22.Reader;

/**
 * This class is used to handle user input.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class InputHandler {
    private static final String STRING_CONSTANT = "CONSTANT";

    /**
     * Asking for manual entering of string value or using a constant value.
     * */
    public static void handleInput(){
        printMenu();
        UserChoice userChoice = UserChoice.EXIT;
        int userChoiceIndex = Reader.askForInt("Your choice:");
        try {
             userChoice = UserChoice.values()[userChoiceIndex-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your choice is incorrect");
        }

        switch (userChoice) {
            case USING_CONSTANT:
                handleConstantString();
                break;
            case MANUAL_INPUT:
                handleUserString();
                break;
            case EXIT:
                break;
        }
    }

    private static void handleConstantString() {
        changeString(STRING_CONSTANT);
    }

    private static void handleUserString() {
        String originalString = Reader.askForString("Enter your string: ");
        changeString(originalString);
    }

    private static void changeString(String string) {
        System.out.println("Original string: " + string);
        char[] swapValues = handleChangeInput();
        StringChanger.changeStringReflection(string, swapValues);
        System.out.println("Changed string: " + string);
    }

    /** This method reads 2 char value: original and replacement value*/
    private static char[] handleChangeInput() {
        System.out.println("Enter characters to change: ");
        char[] swapValues = new char[2];
        swapValues[0] = Reader.askForChar("Original character:");
        swapValues[1] = Reader.askForChar("Replacement character: ");
        return swapValues;
    }

    private static void printMenu() {
        System.out.println("Select how to initialize string: ");
        System.out.println("1. Using constant");
        System.out.println("2. Manual input");
        System.out.println("3. Exit");
    }
}


enum UserChoice {
    USING_CONSTANT,
    MANUAL_INPUT,
    EXIT
}
