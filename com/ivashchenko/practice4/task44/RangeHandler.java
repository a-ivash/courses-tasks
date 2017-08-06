package com.ivashchenko.practice4.task44;

import com.ivashchenko.practice2.task22.Reader;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This class handles user's input and creates lists and sets of specified size and range.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class RangeHandler {
    private static AbstractRangeGenerator lRangeGenerator = new ListRangeGenerator();
    private static AbstractRangeGenerator sRangeGenerator = new SetRangeGenerator();
    public static void handleRange() {


        int numberOfElements = Reader.askForInt("Enter number of elements: ");
        int startOfRange = Reader.askForInt("Enter start of range: ");
        int endOfRange = Reader.askForInt("Enter start of range: ");

        try {
            List<Number> numbers1 = (List<Number>)lRangeGenerator.getRandomRange(numberOfElements, startOfRange, endOfRange);
            printCollection("LIST", numbers1);
            Set<Number> numbers2 = (Set<Number>)sRangeGenerator.getRandomRange(numberOfElements, startOfRange, endOfRange);
            printCollection("SET", numbers2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void printCollection(String collectionType, Collection<Number> numbers) {
        System.out.println("Collection: " + collectionType);
        System.out.println(numbers);
    }
}
