package com.ivashchenko.practice3.task31;


import com.ivashchenko.practice2.task22.Reader;

import java.util.Arrays;

public class SearchHandler {
    private static CellPhone[] allCellPhones = CellPhoneReader.getRandomCellPhones(10);

    public static void handleSearch() {
        printAllCellPhones();

        String componentName;
        while (!(componentName = Reader.askForString("Enter component name or <EXIT>: ")).equals("EXIT")) {
            componentName = componentName.toLowerCase();
            CellPhone[] cellPhonesWithComponent = getCellPhonesContainingComponent(componentName);
            printResults(cellPhonesWithComponent);
        }
    }

    private static CellPhone[] getCellPhonesContainingComponent(String componentName) {
        // Only arrays can be used
        CellPhone[] cellPhones = new CellPhone[allCellPhones.length];
        int counter = 0;
        for (CellPhone cellPhone: allCellPhones) {
            if (cellPhone.hasComponent(componentName)) {
                cellPhones[counter++] = cellPhone;
            }
        }
        return Arrays.copyOf(cellPhones, counter);
    }

    private static void printAllCellPhones() {
        printCellPhones(allCellPhones);
    }

    private static void printCellPhones(CellPhone[] cellPhones) {
        for (CellPhone cellPhone: cellPhones) {
            printLongLine('*');
            System.out.println(cellPhone);
            printLongLine('*');
        }
    }

    private static void printResults(CellPhone[] cellPhones) {
        printLongLine('#');
        if (cellPhones.length == 0) {
            printNoResults();
        } else {
            printCellPhones(cellPhones);
        }
        printLongLine('#');
    }

    private static void printNoResults() {
        System.out.println("Your query has no results.");
    }

    /* Used for simplify reading the output */
    private static void printLongLine(char separator) {
        for (int i = 0; i < 50; i++) {
            System.out.print(separator);
        }
        System.out.println();
    }
}
