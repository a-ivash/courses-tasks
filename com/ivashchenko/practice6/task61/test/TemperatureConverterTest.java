import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Testing converting method from TemperatureConverter class. */
public class TemperatureConverterTest {
    private static TemperatureConverter converter;

    @BeforeClass
    public static void initFields() {
        converter = new TemperatureConverter();
    }

    @Test
    public void testFahrenheitCelsiusConvert() {
        double fahrenheit = 100.0;
        double actual = converter.convertFtoC(fahrenheit);
        double expected = 37.7777;
        double delta = 10E-4;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testCelsiusFahrenheitConvert() {
        double celsius = 100.0;
        double actual = converter.convertCtoF(celsius);
        double expected = 212.0;
        double delta = 10E-4;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testCelsiusKelvinConvert() {
        double celsius = 100.0;
        double actual = converter.convertCtoK(celsius);
        double expected = 373.2;
        double delta = 10E-1;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testKelvinCelsiusConvert() {
        double kelvin = 100.0;
        double actual = converter.convertKtoC(kelvin);
        double expected = -173.2;
        double delta = 10E-1;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFahrenheitKelvinConvert() {
        double fahrenheit = 100.0;
        double actual = converter.convertFtoK(fahrenheit);
        double expected = 310.9444;
        double delta = 10E-4;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testKelvinFahrenheitConvert() {
        double kelvin = 100.0;
        double actual = converter.convertKtoF(kelvin);
        double expected = -279.7;
        double delta = 10E-1;
        assertEquals(expected, actual, delta);
    }
}
