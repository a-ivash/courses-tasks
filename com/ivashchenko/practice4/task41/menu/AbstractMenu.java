package com.ivashchenko.practice4.task41.menu;

import java.util.Scanner;

/**
 * This abstract class is used to define basic elements of menu.
 * It also contains methods to simplify obtaining of value from user.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public abstract class AbstractMenu {
    protected Scanner scanner;
    protected String[] menuItems;


    public AbstractMenu(){
        this.scanner = new Scanner(System.in);
    }

    public void printMenu() {
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(String.format("%d. %s", i+1, menuItems[i]));
        }
    }

    public int askForIntInput(String question){
        System.out.print(question);
        return scanner.nextInt();
    }


    public String askForStringInput(String question){
        System.out.print(question);
        return scanner.next();
    }

    /** Override to define new menu. */
    public abstract void handleMenu();
}
