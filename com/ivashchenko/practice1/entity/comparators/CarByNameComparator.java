package com.ivashchenko.practice1.entity.comparators;

import com.ivashchenko.practice1.entity.Car;

import java.util.Comparator;
/**
 * This class is used to compare cars by manufacturer name.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class CarByNameComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        String name1 = o1.getManufacturerName().toLowerCase();
        String name2 = o2.getManufacturerName().toLowerCase();
        return name1.compareTo(name2);
    }
}
