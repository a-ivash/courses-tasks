import org.apache.log4j.Logger;

/**
 * This class is used to convert decimal representation of number to binary/octal/hexadecimal representation.
 * This class supports common loop for finding representation with specified base.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class NumberConverter {
    /** Length of some string is a base for this type of numeral system. */
    private static final String binaryCodes = "01";
    private static final String octalCodes = "01234567";
    private static final String hexCodes = octalCodes + "89ABCDEF";
    private static Logger logger = Logger.getLogger(NumberConverter.class);

    private static String getRepresentationName(String codes) {
        int size = codes.length();
        switch (size) {
            case 2: return "binary";
            case 8: return "octal";
            default: return "hex";
        }
    }

    private static String getRepresentation(final int number, String codes) {
        String infoMessage = String.format("Trying to represent %d in %s codes", number, getRepresentationName(codes));
        logger.info(infoMessage);

        if (number < 0) {
           logger.error("Input number < 0");
           return "ERROR";
        }

        StringBuilder sb = new StringBuilder();
        int workingNumber = number;
        while (workingNumber > 0) {
            sb.append(codes.charAt(workingNumber % codes.length()));
            workingNumber /= codes.length();
        }

        String answer = sb.reverse().toString();
        logger.info("Successfully decode " + number + " into " + answer);
        return answer;
    }

    public static String getBinaryRepresentation(int number){
        return getRepresentation(number, binaryCodes);
    }

    public static String getOctalRepresentation(int number){
        return getRepresentation(number, octalCodes);
    }

    public static String getHexRepresentation(int number){
        return getRepresentation(number, hexCodes);
    }
}
