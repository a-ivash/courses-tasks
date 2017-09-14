package menu;

import java.util.Scanner;

/**
 * This abstract class is used to define basic elements of menu.
 * It also contains methods to simplify obtaining of value from user.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public abstract class AbstractMenu {
    protected Scanner scanner;

    public AbstractMenu(){
        this.scanner = new Scanner(System.in);
    }

    public int askForIntInput(String question){
        System.out.print(question);
        return scanner.nextInt();
    }

    public String askForStringInput(String question){
        System.out.print(question);
        return scanner.next();
    }

    public abstract void printMenu();

    /** Override to define new menu. */
    public abstract void handleMenu();
}
