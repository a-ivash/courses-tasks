import entity.Car;
import entity.enums.Color;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/** This class tests Car entity defined in Car.java */
public class TestCarEntity {
    // Data for sample car
    private static final String MANUFACTURER_NAME = "BMW";
    private static final String MODEL_NAME = "M3";
    private static final int PRODUCTION_YEAR = 2012;
    private static final Color CAR_COLOR = Color.BLACK;
    private static final String REGISTRATION_NUMBER = "1357908642";
    private static final int PRICE = 35_000;
    private static Car testCar;

    @BeforeClass
    public static void initFields() {
        testCar = new Car(MANUFACTURER_NAME, MODEL_NAME,
                          PRODUCTION_YEAR, CAR_COLOR,
                          REGISTRATION_NUMBER, PRICE);
    }

    @Test
    public void testCarOlderThan() {
        int age = 1;
        assertTrue(testCar.isOlderThan(age));
    }

    @Test
    public void testCarNotOlderThan() {
        int age = 10;
        assertFalse(testCar.isOlderThan(age));
    }

    @Test
    public void testCarForNegativeAge() {
        int age = -10;
        assertTrue(testCar.isOlderThan(age));
    }

    @Test
    public void testCloning() throws CloneNotSupportedException{
        Car cloneCar = (Car)testCar.clone();
        assertNotSame(cloneCar, testCar);
    }

    @Test
    public void testCarToString() {
        String expected = "[" + MANUFACTURER_NAME + " " +
                            MODEL_NAME + " - " + PRODUCTION_YEAR + " - $" + PRICE + "]";
        String actual = testCar.toString();
        assertEquals(expected, actual);
    }
}
