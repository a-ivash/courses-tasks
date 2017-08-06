package com.ivashchenko.practice4.task42;

import java.lang.reflect.Field;

/**
 * This class uses Reflection API to change value of string.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class StringChanger {
    /**
     * swapValue is an array of 2 chars.
     * swapValue[0] must be changed into swapValue[1]
     * */
    public static void changeStringReflection(String input, char[] swapValues) {
        Class cl = input.getClass();
        try {
            Field field = cl.getDeclaredField("value");
            field.setAccessible(true);
            char[] chars = (char[])field.get(input);
            replaceInCharArray(chars, swapValues);
            field.set(input, chars);
        } catch (NoSuchFieldException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }

    }

    private static void replaceInCharArray(char[] chars, char[] swapValues) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == swapValues[0]) {
                chars[i] = swapValues[1];
            }
        }
    }
}
