package com.ivashchenko.practice4.task43;

import java.lang.reflect.*;

/**
 * This class creates instance of Day.class using Reflection API.
 * It provides method to print inner state information of created object.
 * It provides interface to set one of values using Reflection API.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class ReflectionDayCreator {
    private Object targetObject;
    private Class targetClass = Day.class;

    public ReflectionDayCreator() {
        targetObject = getObjectForClass();
    }

    /**
     * Creates and returns object using reflection.
     * */
    private Object getObjectForClass() {
        Object obj = null;
        try {
            Class<?>[] params = {int.class};
            Constructor constructor = targetClass.getDeclaredConstructor(params);
            obj = constructor.newInstance(10);
        } catch (NoSuchMethodException e) {

        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {

        }
        return obj;
    }

    private Method getGetterMethod(Method[] methods) throws Exception{
        for (Method method: methods) {
            // returning the first setter method accepting 1 parameter.
            if (method.getParameterCount() == 1) {
                return method;
            }
        }
        throw new Exception("No getter methods");
    }

    /** Uses to demonstrate change of integer value field*/
    public void setIntValueToField(int value) {
        try {
            Method method = getGetterMethod(targetClass.getDeclaredMethods());
            method.invoke(targetObject, value);
            System.out.println("\n\n\n\n" + method.getName() + " was invoked!\n\n\n");
        } catch (Exception e) {

        }
    }

    /** Public interface to display information about object including constructors,
     * methods and variables.*/
    public void printInformationAboutObject() {
        Class cl = targetObject.getClass();
        printConstructors(cl.getConstructors());
        printMethods(cl.getDeclaredMethods());
        printFields(cl.getDeclaredFields());
    }

    private void printConstructors(Constructor[] constructors) {
        for(Constructor constructor: constructors) {
            printConstructor(constructor);
        }
    }

    private void printConstructor(Constructor constructor) {
        System.out.println("************************");
        System.out.println("Constructor");
        printModifiers(constructor.getModifiers());
        printParametersType(constructor.getParameters());
        System.out.println("************************");
    }


    private void printMethods(Method[] methods) {
        for(Method method: methods) {
            printMethod(method);
        }
    }

    private void printMethod(Method method) {
        System.out.println("************************");
        System.out.println("Method: " + method.getName());
        printReturnType(method.getAnnotatedReturnType());
        printModifiers(method.getModifiers());
        printParametersType(method.getParameters());
        System.out.println("************************");
    }

    private void printModifiers(int modifiers) {
        System.out.print("Modifiers:\n\t");
        if (Modifier.isAbstract(modifiers)) {
            System.out.print("abstract ");
        }
        if (Modifier.isStatic(modifiers)) {
            System.out.print("static ");
        }
        if (Modifier.isPublic(modifiers)) {
            System.out.print("public ");
        }
        if (Modifier.isProtected(modifiers)) {
            System.out.print("protected ");
        }
        if (Modifier.isPrivate(modifiers)) {
            System.out.print("private ");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.print("final ");
        }
        System.out.println();
    }

    private void printReturnType(AnnotatedType type){
        System.out.println("Return type: " + type.getType().getTypeName());
    }

    private void printParametersType(Parameter[] parameters) {
        System.out.print("Parameters:\n\t");
        if(parameters.length == 0) {
            System.out.println("No parameters");
            return;
        }
        for(int i = 0; i < parameters.length; i++) {
            String typeName = parameters[i].getType().getTypeName();
            String argName = parameters[i].getName();
            System.out.print(typeName + " " + argName + ((i < parameters.length - 1) ? " | " : ""));
        }
        System.out.println();
    }

    private void printFields(Field[] fields) {
        System.out.println("\n\n\nFIELDS\n");
        for(Field field: fields) {
            printField(field);
        }
    }

    private void printField(Field field) {
        field.setAccessible(true);
        printModifiers(field.getModifiers());
        System.out.println("Type: " + field.getAnnotatedType().getType().getTypeName());
        System.out.println("Field: " + field.getName());
        try {
            System.out.println("Value: " + field.get(targetObject));
        } catch (IllegalAccessException e) {

        }
        System.out.println("________________________________");
    }
}
