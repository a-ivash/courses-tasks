package com.ivashchenko.practice4.task41.menu;

import com.ivashchenko.practice2.task22.Reader;
import com.ivashchenko.practice4.task41.entity.Car;
import com.ivashchenko.practice4.task41.entity.Shop;
import com.ivashchenko.practice4.task41.entity.comparators.CarByNameComparator;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is used to define car shop menu and handle user input.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class ShopMenu extends AbstractMenu {
    Shop shop;
    public ShopMenu(Shop shop) {
        this.shop = shop;
        menuItems = new String[]{
                "Get cars of model older than N years. ",
                "Get cars of specified production year with price > N. ",
                "Print cars sorted by name. ",
                "Print cars sorted by price.",
                "Print cars sorted by production year",
                "Write cars collection to file",
                "Read cars collection from file",
                "EXIT"
        };
    }

    public void printCars(List<Car> cars) {
        for (Car car: cars) {
            System.out.println(car);
        }
    }

    private void queryCarsOfModelOlderThan(){
        String modelName = askForStringInput("Enter model name: ");
        int age = askForIntInput("Enter age: ");

        List<Car> query = shop.getCarsOfModel(modelName);
        query = shop.getCarsOlderThan(query, age);
        if (query.isEmpty()) {
            System.out.println("No cars with specified parameters.");
        } else {
            printCars(query);
        }
    }

    private void queryCarsOfYearExpensiveThan() {
        int productionYear = askForIntInput("Enter production year: ");
        int price = askForIntInput("Enter price: ");

        List<Car> query = shop.getCarsOfProductionYear(productionYear);
        query = shop.getCarsExpensiveThan(query, price);
        if (query.isEmpty()) {
            System.out.println("No cars with specified parameters.");
        } else {
            printCars(query);
        }
    }

    private void queryCarsSortedByName() {
        List<Car> allCars = shop.getAllCars();
        Collections.sort(allCars, new CarByNameComparator());
        printCars(allCars);
    }

    private void queryCarsSortedByPrice() {
        List<Car> allCars = shop.getAllCars();
        Collections.sort(allCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        printCars(allCars);
    }

    private void queryCarsSortedByProductionYear() {
        List<Car> allCars = shop.getAllCars();
        Collections.sort(allCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getProductionYear() - o2.getProductionYear();
            }
        });
        printCars(allCars);
    }


    private void readCarsFromFile() {
        String fileName = Reader.askForString("Enter filename: ");
        try {
            shop.readCarsFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Your filename is not valid.");
        } catch (ClassNotFoundException e) {
            System.out.println("File has incorrect format.");
        }
    }

    private void writeCarsToFile() {
        String fileName = Reader.askForString("Enter filename: ");
        shop.writeCarsToFile(fileName);
    }

    @Override
    public void handleMenu() {
        MenuItems menuItem = MenuItems.EXIT;
        do {
            printMenu();
            int input = scanner.nextInt();
            if ((input < 0) || (input > MenuItems.values().length)) {
                input = MenuItems.EXIT.ordinal() + 1;
            }

            menuItem = MenuItems.values()[input-1];
            switch (menuItem) {
                case MODEL_AND_OLDER_THAN:
                    queryCarsOfModelOlderThan();
                    break;
                case OF_YEAR_EXPENSIVE_THAN:
                    queryCarsOfYearExpensiveThan();
                    break;
                case SORT_BY_MANUFACTURER:
                    queryCarsSortedByName();
                    break;
                case SORT_BY_PRICE:
                    queryCarsSortedByPrice();
                    break;
                case SORT_BY_PRODUCTION_YEAR:
                    queryCarsSortedByProductionYear();
                    break;
                case WRITE_TO_FILE:
                    writeCarsToFile();
                    break;
                case READ_FROM_FILE:
                    readCarsFromFile();
                    break;
                case EXIT:
                    return;
            }
        } while (true);
    }
}
