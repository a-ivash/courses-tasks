package com.ivashchenko.practice1.entity.utils;

import com.ivashchenko.practice1.entity.Car;
import com.ivashchenko.practice1.entity.enums.Color;

import java.util.Random;

/**
 * This class is used to generate random values and cars.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class RandomValueGenerator {
    private static int getRandomYear(){
        Random random = new Random();
        return 1950 + random.nextInt(100) % 67;
    }

    private static Color getRandomColor() {
        Random random = new Random();
        return Color.values()[random.nextInt(Color.values().length)];
    }

    private static int getRandomPrice() {
        return new Random().nextInt(100000);
    }

    private static String getRandomManufacturer() {
        String[] manufacturers = {"BMW", "Mercedes", "AUDI", "Cadillac", "Fiat", "Porsche"};
        return manufacturers[new Random().nextInt(manufacturers.length)];
    }

    private static String getRandomModel(){
        String[] models = {"M3", "M1", "A8", "QQ", "OP"};
        return models[new Random().nextInt(models.length)];
    }

    private static String getRandomRegNumber(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(letters.charAt(new Random().nextInt(letters.length())));
        }
        return sb.toString();
    }


    public static Car getRandomCar(){
        return new Car(getRandomManufacturer(), getRandomModel(), getRandomYear(), getRandomColor(),
                getRandomRegNumber(), getRandomPrice());
    }
}
