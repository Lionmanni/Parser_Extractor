import java.util.HashMap;
import java.util.Map;

public class ElementInfo {
    private String name;
    private String type;
    private String returnType; // for methods
    private int startLine;
    private int endLine;
    private Map<String, String> parameters; // new field for method parameters


    public ElementInfo(String name, String type, String returnType, int startLine, int endLine) {
        this.name = name;
        this.type = type;
        this.returnType = returnType;
        this.startLine = startLine;
        this.endLine = endLine;
        this.parameters = new HashMap<>(); // initialize the parameters map
    }

    // If you have a scenario where returnType is not available/needed upon object creation,
    // you can use this constructor instead

    // existing getters and setters...

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }

    public void addParameter(String parameterName, String parameterType) {
        this.parameters.put(parameterName, parameterType);
    }

    // Optionally, you might want to override the toString method to print this object conveniently
    @Override
    public String toString() {
        // Construct your string representation here. This is a basic example.
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
                .append(", Type: ").append(type)
                .append(", Return Type: ").append(returnType)
                .append(", Start Line: ").append(startLine)
                .append(", End Line: ").append(endLine)
                .append(", Parameters: ").append(parameters.toString());
        return sb.toString();
    }
}