public class CalculatorArguments {
    private String functionName;
    private double functionArgument;
    private int scale;
    private boolean argumentInDegrees;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public double getFunctionArgument() {
        return functionArgument;
    }

    public void setFunctionArgument(double functionArgument) {
        this.functionArgument = functionArgument;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isArgumentInDegrees() {
        return argumentInDegrees;
    }

    public void setArgumentInDegrees(boolean argumentInDegrees) {
        this.argumentInDegrees = argumentInDegrees;
    }

    public void convertArgumentToRadians() {
        if(argumentInDegrees) {
            argumentInDegrees = false;
            functionArgument = Math.toRadians(functionArgument);
        }
    }
}
