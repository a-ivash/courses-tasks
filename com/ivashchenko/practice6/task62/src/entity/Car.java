package entity;

import entity.enums.Color;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class is used to represent information about car.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Car implements Cloneable, Serializable {
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
//        return super.toString();
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

    public boolean isOlderThan(int age){
        if (age < 0) {
            return true;
        }
        return productionYear < LocalDate.now().getYear() - age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Car(manufacturerName, modelName, productionYear, color, registrationNumber, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Car)) {
            return false;
        }
        Car car = (Car)obj;

        if (!car.getManufacturerName().equals(getManufacturerName())) {
            return false;
        }

        if (!car.getModelName().equals(getModelName())) {
            return false;
        }

        if (car.getProductionYear() != getProductionYear()) {
            return false;
        }

        if (car.getPrice() != getPrice()) {
            return false;
        }

        if (car.getColor() != getColor()) {
            return false;
        }

        return car.getRegistrationNumber().equals(getRegistrationNumber());
    }
}
