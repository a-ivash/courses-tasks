
import entity.Car;
import entity.Shop;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


/** This class is used to test some methods from Shop class. */
public class TestShop {
    private static Shop shop;

    @BeforeClass
    public static void initFields() {
        shop = new Shop();
    }

    @Before
    public void testAtLeast2CarsExists() {
        int numberOfCars = shop.queryAllCars().size();
        assertTrue(numberOfCars >= 2);
    }

    @Test
    public void testSortingCarsByPriceAscending() {
        List<Car> cars = shop.queryCarsSortedByPriceAsc();
        int price1 = cars.get(0).getPrice();
        int price2 = cars.get(1).getPrice();
        assertTrue(price1 <= price2);
    }

    @Test
    public void testSortingCarsByPriceDescending() {
        List<Car> cars = shop.queryCarsSortedByPriceDesc();
        int price1 = cars.get(0).getPrice();
        int price2 = cars.get(1).getPrice();
        assertTrue(price1 >= price2);
    }

    @Test
    public void testCorrectSerializeDeserialize() throws FileNotFoundException,
                                                         ClassNotFoundException {
        String fileName = "cars.dat";
        int expectedNumberOfCars = shop.queryAllCars().size();
        shop.writeCarsToFile(fileName);

        shop = new Shop();
        shop.readCarsFromFile(fileName);

        int actualNumberOfCars = shop.queryAllCars().size();
        assertEquals(expectedNumberOfCars, actualNumberOfCars);
    }

    @Test
    public void testCarsOlderThan() {
        int age = 35;
        List<Car> oldCars = shop.queryCarsOlderThan(age);
        for (Car car: oldCars) {
            assertTrue(car.getProductionYear() < LocalDate.now().getYear() - age);
        }
    }

    @Test
    public void testNegativeAge() {
        int age = -10;
        List<Car> oldCars = shop.queryCarsOlderThan(age);
        assertEquals(0, oldCars.size());
    }


    @Test
    public void testCarsSortedByProductionYear() {
        List<Car> cars = shop.queryCarsSortedByProductionYear();
        for (int i = 0; i < cars.size() - 1; i++) {
            Car car1 = cars.get(i);
            Car car2 = cars.get(i + 1);
            assertTrue(car1.getProductionYear() <= car2.getProductionYear());
        }
    }

    @Test
    public void testCarsSortedByManufacturerName() {
        List<Car> cars = shop.queryCarsSortedByManufacturerName();
        String manufacturer1 = cars.get(0).getManufacturerName();
        String manufacturer2 = cars.get(1).getManufacturerName();
        int compareValue = manufacturer1.compareTo(manufacturer2);
        assertTrue(compareValue <= 0);
    }

    @Test
    public void testQueryCarsForNegativePrice() {
        int price = -1000;
        List<Car> cars = shop.queryCarsExpensiveThan(price);
        assertEquals(0, cars.size());
    }


    @Test
    public void testAllCarsListNotSame() {
        List<Car> cars1 = shop.queryAllCars();
        List<Car> cars2 = shop.queryAllCars();
        assertNotSame(cars1, cars2);
    }

    @Test
    public void testAllCarsNotSame() {
        List<Car> cars1 = shop.queryAllCars();
        List<Car> cars2 = shop.queryAllCars();

        for (int i = 0; i < cars1.size(); i++) {
            assertNotSame(cars1.get(i), cars2.get(i));
        }
    }

    @Test
    public void testAllCarsEquals() {
        List<Car> cars1 = shop.queryAllCars();
        List<Car> cars2 = shop.queryAllCars();

        for (int i = 0; i < cars1.size(); i++) {
            assertEquals(cars1.get(i), cars2.get(i));
        }
    }


    @Test
    public void testQueryCarsOfModel() {
        String modelName = "Cadillac";
        List<Car> cars = shop.queryCarsOfModel(modelName);
        for (Car car: cars) {
            assertEquals(modelName, car.getManufacturerName());
        }
    }

    @Test
    public void testQueryCarsOfProductionYear() {
        int year = 1969;
        List<Car> cars = shop.queryCarsOfProductionYear(year);
        for (Car car: cars) {
            assertEquals(year, car.getProductionYear());
        }
    }
}
