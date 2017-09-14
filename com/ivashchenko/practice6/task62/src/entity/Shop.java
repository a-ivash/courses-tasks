package entity;

import entity.comparators.CarByNameComparator;
import entity.utils.RandomValueGenerator;
import menu.AbstractMenu;
import menu.ShopMenu;

import java.io.FileNotFoundException;
import java.util.*;


/**
 * This class is used to store car objects and perform queries on this cars.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Shop {
    /** Used to store all allCars */
    private List<Car> allCars;
    /** Used to print menu and proceed user's input.*/
    private AbstractMenu menu;

    public Shop(){
        menu = new ShopMenu(this);

        allCars = new LinkedList<>();
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
        allCars.add(RandomValueGenerator.getRandomCar());
    }

    public List<Car> queryAllCars() {
        List<Car> cars = new LinkedList<>();
        for (Car car: allCars) {
            try {
                cars.add((Car)car.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        return cars;
    }

    public List<Car> queryCarsSortedByManufacturerName() {
        List<Car> allCars = queryAllCars();
        Collections.sort(allCars, new CarByNameComparator());
        return allCars;
    }

    public List<Car> queryCarsOlderThan(int age) {
        return queryCarsOlderThan(allCars, age);
    }

    public List<Car> queryCarsOlderThan(List<Car> cars, int age) {
        List<Car> query = new LinkedList<>();
        if (age < 0) { // can't compare to negative age
            return query;
        }

        for (Car car: cars) {
            if (car.isOlderThan(age)) {
                try {
                    query.add((Car)car.clone());
                }catch (CloneNotSupportedException e) { }
            }
        }
        return query;
    }

    public List<Car> queryCarsOfModel(String modelName){
        return queryCarsOfModel(allCars, modelName);
    }


    public List<Car> queryCarsOfModel(List<Car> cars, String modelName){
        List<Car> query = new LinkedList<>();

        for (Car car: cars) {
            if (car.getModelName().equals(modelName)) {
                try {
                    query.add((Car)car.clone());
                }catch (CloneNotSupportedException e) { }
            }
        }
        return query;
    }


    public List<Car> queryCarsOfProductionYear(int year){
        return this.queryCarsOfProductionYear(allCars, year);
    }

    public List<Car> queryCarsOfProductionYear(List<Car> cars, int year){
        List<Car> query = new LinkedList<>();
        for(Car car: cars){
            if (car.getProductionYear() != year) {
                continue;
            }
            try {
                query.add((Car)car.clone());
            }catch (CloneNotSupportedException e) { }
        }
        return query;
    }

    public List<Car> queryCarsExpensiveThan(int price){
        return queryCarsExpensiveThan(allCars, price);
    }


    public List<Car> queryCarsExpensiveThan(List<Car> cars, int price) {
        List<Car> query = new LinkedList<>();
        if (price < 0) {
            return query;
        }

        for (Car car: cars){
            if (car.getPrice() < price) {
                continue;
            }
            try {
                query.add((Car)car.clone());
            }catch (CloneNotSupportedException e) { }
        }
        return query;
    }

    public List<Car> queryCarsSortedByPriceAsc() {
        List<Car> allCars = queryAllCars();
        Collections.sort(allCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        return allCars;
    }

    public List<Car> queryCarsSortedByPriceDesc() {
        List<Car> allCars = queryAllCars();
        Collections.sort(allCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
        return allCars;
    }

    public List<Car> queryCarsSortedByProductionYear() {
        List<Car> allCars = queryAllCars();
        Collections.sort(allCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getProductionYear() - o2.getProductionYear();
            }
        });
        return allCars;
    }

    public void readCarsFromFile(String fileName) throws FileNotFoundException, ClassNotFoundException {
        try {
            allCars = CarReader.readCarsFromFile(fileName);
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw e;
        }
    }

    public void writeCarsToFile(String fileName) {
        List<Car> cars = new ArrayList<>(allCars/*.subList(0, 3)*/);
        CarReader.writeCarsToFile(fileName, cars);
    }

    public void handleMenu() {
        menu.handleMenu();
    }
}
