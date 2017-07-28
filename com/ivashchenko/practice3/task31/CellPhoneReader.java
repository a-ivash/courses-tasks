package com.ivashchenko.practice3.task31;

import com.ivashchenko.practice2.task22.Reader;

/**
 * This class provides interface to read data to create new cell phone.
 * It also has method returning sample cell phone.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class CellPhoneReader {
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

    private static String[] readSpecifications(int numberOfSpecifications) {
        String[] specifications = new String[numberOfSpecifications];
        for (int specificationIndex = 0; specificationIndex < numberOfSpecifications; specificationIndex++) {
            specifications[specificationIndex] = Reader.askForString("Enter specification: ");
        }
        return specifications;
    }


    public static CellPhone getSampleCellPhone() {
        CellPhone cellPhone = new CellPhone("iphone", 2);
        cellPhone.addComponent("Camera", "Makes photo", new String[]{"quality: HQ", "somespec: someval"});
        cellPhone.addComponent("Speaker", "Allows to hear", new String[]{"quality: HQ", "somespec: someval"});
        return cellPhone;
    }
}
