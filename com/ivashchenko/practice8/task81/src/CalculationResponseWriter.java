import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculationResponseWriter {
    private static final String HTML_TEMPLATE = "<html><head></head><body> %s </body></html>";
    private static final String BOLD_MESSAGE_STRING_PATTERN = "<h1><b> %s </b></h1>";
    private static final String ERROR_MESSAGE = String.format(BOLD_MESSAGE_STRING_PATTERN, "Wrong input arguments");
    private static final String ANSWER_MESSAGE_FORMAT_PATTERN = String.format(BOLD_MESSAGE_STRING_PATTERN, "Your answer is %s");
    private static final String HOME_LINK = "<a href='/'>Go home</a>";

    public static void proceedRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(PrintWriter writer = resp.getWriter()){
            String htmlOutput;
            String message = "";
            try{
                String answer = computeValue(req);
                message = String.format(ANSWER_MESSAGE_FORMAT_PATTERN, answer);
            } catch (NumberFormatException | Error e) {
                message = ERROR_MESSAGE;
            } finally {
                message += HOME_LINK;
                htmlOutput = String.format(HTML_TEMPLATE, message);
                writer.write(htmlOutput);
            }
        }
    }

    private static String computeValue(HttpServletRequest request) throws NumberFormatException{
        CalculatorArguments calculatorArguments = CalculationRequestReader.getArgumentsFromRequest(request);
        return Calculator.calculateValue(calculatorArguments);
    }
}
