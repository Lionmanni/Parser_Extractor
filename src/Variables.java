import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;


public class Variables {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here


        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, object_def> variableMap = new HashMap<>();
        // Was ist ein Visitor
        VariableVisitor visitor = new VariableVisitor();

        visitor.visit(cu, variableMap);

        for(Map.Entry<String, object_def> entry : variableMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class VariableVisitor extends VoidVisitorAdapter<Map<String, object_def>> {
        @Override
        public void visit(VariableDeclarator n, Map<String, object_def> variableMap) {
            String varname = n.getNameAsString();
            String vartype = n.getTypeAsString();

            int startline = n.getBegin().map(pos -> pos.line).orElse(-1);
            int endline = n.getEnd().map(pos -> pos.line).orElse(-1);

            object_def customValue = variableMap.getOrDefault(varname, new object_def(vartype, new ArrayList<>()));
            customValue.getPairList().add(new SimpleEntry<>(startline, endline));

            variableMap.put(varname, customValue);
            super.visit(n, variableMap);
        }
    }
}
