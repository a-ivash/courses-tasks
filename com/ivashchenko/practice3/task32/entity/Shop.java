package com.ivashchenko.practice3.task32.entity;

import com.ivashchenko.practice3.task32.entity.utils.RandomValueGenerator;
import com.ivashchenko.practice3.task32.menu.Menu;
import com.ivashchenko.practice3.task32.menu.ShopMenu;

import java.util.LinkedList;
import java.util.List;


/**
 * This class is used to store car objects and perform queries on this cars.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Shop {
    /** Used to store all allCars */
    private List<Car> allCars;
    /** Used to print menu and proceed user's input.*/
    private Menu menu;

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

    public List<Car> getAllCars() {
        List<Car> cars = new LinkedList<>();
        for (Car car: allCars) {
            try {
                cars.add((Car)car.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        return cars;
    }

    public List<Car> getCarsOlderThan(int year) {
        return getCarsOlderThan(allCars, year);
    }

    public List<Car> getCarsOlderThan(List<Car> cars, int year) {
        List<Car> query = new LinkedList<>();
        for (Car car: cars) {
            if (car.isOlderThan(year)) {
                try {
                    query.add((Car)car.clone());
                }catch (CloneNotSupportedException e) { }
            }
        }
        return query;
    }

    public List<Car> getCarsOfModel(String modelName){
        return getCarsOfModel(allCars, modelName);
    }


    public List<Car> getCarsOfModel(List<Car> cars, String modelName){
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


    public List<Car> getCarsOfProductionYear(int year){
        return getCarsOfProductionYear(allCars, year);
    }

    public List<Car> getCarsOfProductionYear(List<Car> cars, int year){
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

    public List<Car> getCarsExpensiveThan(int price){
        return getCarsExpensiveThan(allCars, price);
    }


    public List<Car> getCarsExpensiveThan(List<Car> cars, int price) {
        List<Car> query = new LinkedList<>();
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

    public void handleMenu() {
        menu.handleMenu();
    }
}
