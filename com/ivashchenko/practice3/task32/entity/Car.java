package com.ivashchenko.practice3.task32.entity;

import com.ivashchenko.practice3.task32.entity.enums.Color;

import java.time.LocalDate;

/**
 * This class is used to represent information about car.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Car implements Cloneable{
    private final String manufacturerName;
    private final String modelName;
    private final int productionYear;
    private Color color;
    private String registrationNumber;
    private int price;

    public Car(String manufacturerName, String modelName, int productionYear,
               Color color, String registrationNumber, int price) {
        this.manufacturerName = manufacturerName;
        this.modelName = modelName;
        this.productionYear = productionYear;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("[%s %s - %d - $%d]", manufacturerName, modelName, productionYear, price);
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getModelName() {
        return modelName;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Color getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOlderThan(int year){
        return productionYear < LocalDate.now().getYear() - year;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Car(manufacturerName, modelName, productionYear, color, registrationNumber, price);
    }
}
