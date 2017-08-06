package com.ivashchenko.practice4.task43;

import com.ivashchenko.practice2.task22.Reader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;

/**
 * This class runs methods to demonstrate object's inner state
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class ReflectionDemo {
    public static void main(String[] args) {
        ReflectionDayCreator rdc = new ReflectionDayCreator();
        rdc.printInformationAboutObject();

        try {
            int newIntValue = Reader.askForInt("Enter new int value");
            rdc.setIntValueToField(newIntValue);
            rdc.printInformationAboutObject();
        } catch (InputMismatchException e) {
            System.out.println("Not correct int value");
        }

    }
}
