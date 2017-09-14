public class Calculator {
    private static double computeValueForFunction(CalculatorArguments arguments) {
        String function = arguments.getFunctionName();
        double value = arguments.getFunctionArgument();
        switch (function) {
            case "sin": return Math.sin(value);
            case "cos": return Math.cos(value);
            case "tan": return Math.tan(value);
            case "asin": return Math.asin(value);
            case "acos": return Math.acos(value);
            case "atan": return Math.atan(value);
            default: throw new Error("No function specified");
        }
    }

    private static String getStringOfValueWithScale(double value, int scale) {
        String formatStr = "%." + scale + "f";
        return String.format(formatStr, value);
    }

    public static String calculateValue(CalculatorArguments arguments) {
        arguments.convertArgumentToRadians();
        double computedValue = computeValueForFunction(arguments);
        return getStringOfValueWithScale(computedValue, arguments.getScale());
    }
}
