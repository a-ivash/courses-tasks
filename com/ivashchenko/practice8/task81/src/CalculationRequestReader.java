import javax.servlet.http.HttpServletRequest;

public class CalculationRequestReader {
    private static final String FUNCTION_HTML_FORM_NAME = "functionName";
    private static final String ARGUMENT_HTML_FORM_NAME = "valueToCompute";
    private static final String SCALE_HTML_FORM_NAME = "digitsAfterComma";
    private static final String UNITS_HTML_FORM_NAME = "units";
    private static final String DEGREES_UNIT = "degrees";

    public static CalculatorArguments getArgumentsFromRequest(HttpServletRequest request) throws NumberFormatException {
        String functionName = request.getParameterValues(FUNCTION_HTML_FORM_NAME)[0];
        double functionArgument = Double.valueOf(request.getParameterValues(ARGUMENT_HTML_FORM_NAME)[0]);
        int scale = Integer.valueOf(request.getParameterValues(SCALE_HTML_FORM_NAME)[0]);
        boolean argumentInDegrees = request.getParameterValues(UNITS_HTML_FORM_NAME)[0].equals(DEGREES_UNIT);

        CalculatorArguments arguments = new CalculatorArguments();
        arguments.setFunctionName(functionName);
        arguments.setFunctionArgument(functionArgument);
        arguments.setScale(scale);
        arguments.setArgumentInDegrees(argumentInDegrees);
        return arguments;
    }
}
