import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class Variables {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here


        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, String> variableMap = new HashMap<>();
        // Was ist ein Visitor
        VariableVisitor visitor = new VariableVisitor();

        visitor.visit(cu, variableMap);

        for(Map.Entry<String, String> entry : variableMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class VariableVisitor extends VoidVisitorAdapter<Map<String, String>> {
        @Override
        public void visit(VariableDeclarator vd, Map<String, String> variableMap) {
            variableMap.put(vd.getNameAsString(), vd.getTypeAsString());
            super.visit(vd, variableMap);
        }
    }
}
