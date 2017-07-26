package com.ivashchenko.practice1.converter;

/**
 * This class is used to convert decimal representation of number to binary/octal/hexadecimal representation.
 * This class supports common loop for finding representation with specified base.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class NumberConverter {
    /** Length of some string is a base for this type of numeral system. */
    private static String binaryCodes = "01";
    private static String octalCodes = "01234567";
    private static String hexCodes = octalCodes + "89ABCDEF";

    private static String getRepresentation(int number, String codes) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(codes.charAt(number % codes.length()));
            number /= codes.length();
        }
        return sb.reverse().toString();
    }

    public static String getBinaryRepresentation(int number){
        return getRepresentation(number, binaryCodes);
    }

    public static String getOctalRepresentation(int number){
        return getRepresentation(number, octalCodes);
    }

    public static String getHexRepresentation(int number){
        return getRepresentation(number, hexCodes);
    }
}
