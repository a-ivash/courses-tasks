/**
 * This class is used to demonstrate work with NumberConverter class.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class NumberConverterDemo {
    public static void main(String[] args) {
        for(int number = -1; number < 10; number++) {
            System.out.println("Binary representation: " + NumberConverter.getBinaryRepresentation(number));
            System.out.println("Octal representation: " + NumberConverter.getOctalRepresentation(number));
            System.out.println("Hex representation: " + NumberConverter.getHexRepresentation(number));
        }
    }
}


