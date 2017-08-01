package com.ivashchenko.practice3.task31;

import com.ivashchenko.practice2.task22.Reader;

import java.util.Random;

/**
 * This class provides interface to read data to create new cell phone.
 * It also has method returning sample cell phone.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class CellPhoneReader {
    private static Random random = new Random();
    // Used for random cell phone creation
    private static String[] modelNames = {"Samsung", "LG", "Iphone", "Motorola", "Nokia"};
    private static String[][] components = {{"Camera", "Makes photo"},
                                            {"MP3", "Supports playing music"},
                                            {"Internet browser", "Allows to surf the Internet"},
                                            {"Bluetooth", "Transferring data on short distances"},
                                            {"Wi-Fi", "Connects cell phone to Wi-Fi devices"}};
    private static String[] specifications = {"Spec A: value A", "Spec B: value B", "Spec C: value C"};

    /* Reading information about new cell phone from console. */
    public static CellPhone getNewCellPhone() {
        String modelName = Reader.askForString("Enter model name: ");
        int componentNumber = Reader.askForInt("Enter components number: ");

        CellPhone cellPhone = new CellPhone(modelName, componentNumber);
        for (int componentIndex = 0; componentIndex < componentNumber; componentIndex++) {
            String componentName = Reader.askForString("Enter component name: ");
            String componentDescription = Reader.askForString("Enter component description: ");

            int specNumber = Reader.askForInt("Enter number of specifications: ");
            String[] specifications = readSpecifications(specNumber);
            cellPhone.addComponent(componentName, componentDescription, specifications);
        }
        return cellPhone;
    }

    /* Helper method to read specifications. Called from getNewCellPhone method */
    private static String[] readSpecifications(int numberOfSpecifications) {
        String[] specifications = new String[numberOfSpecifications];
        for (int specificationIndex = 0; specificationIndex < numberOfSpecifications; specificationIndex++) {
            specifications[specificationIndex] = Reader.askForString("Enter specification: ");
        }
        return specifications;
    }

    /**
     * Method used to simplify code for generating random cell phone
     * @return String[] - array of specifications
     * */
    private static String[] getRandomSpecifications(int numberOfSpecifications) {
        String[] tSpecifications = new String[numberOfSpecifications];
        for (int specIndex = 0; specIndex < numberOfSpecifications; specIndex++) {
            tSpecifications[specIndex] = specifications[random.nextInt(specifications.length)];
        }
        return tSpecifications;
    }

    /**
     * This method simply add randomly generated components through public method of cell phone class.
     * */
    private static void addRandomComponents(CellPhone cellPhone, int numberOfComponents) {
        for (int componentIndex = 0; componentIndex < numberOfComponents; componentIndex++) {
            String[] componentNameAndDesc = components[random.nextInt(components.length)];
            String componentName = componentNameAndDesc[0];
            String componentDescription = componentNameAndDesc[1];

            // To generate various number of specifications for different components and models
            int numberOfSpecifications = 1 + random.nextInt(specifications.length - 1);
            String[] tSpecifications = getRandomSpecifications(numberOfSpecifications);
            cellPhone.addComponent(componentName, componentDescription, tSpecifications);
        }
    }

    public static CellPhone getRandomCellPhone() {
        String modelName = modelNames[random.nextInt(modelNames.length)];
        // to have at least one component
        int numberOfComponents = 1 + random.nextInt(components.length-1);
        CellPhone cellPhone = new CellPhone(modelName, numberOfComponents);
        addRandomComponents(cellPhone, numberOfComponents);
        return cellPhone;
    }

    public static CellPhone[] getRandomCellPhones(int number) {
        CellPhone[] cellPhones = new CellPhone[number];
        for (int i = 0; i < number; i++) {
            cellPhones[i] = getRandomCellPhone();
        }
        return cellPhones;
    }
}
