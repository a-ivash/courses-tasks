package entity;

import java.io.*;
import java.util.List;

public class CarReader {
    public static List<Car> readCarsFromFile(String fileName) throws FileNotFoundException, ClassNotFoundException{
        List<Car> cars = null;
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            cars = (List<Car>)objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            // throwing exception upper to user interacting level;
            throw e;
        } catch (IOException e) {

        }
        return cars;
    }

    public static void writeCarsToFile(String fileName, List<Car> cars) {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(cars);
        } catch (IOException e) {

        }
    }
}
