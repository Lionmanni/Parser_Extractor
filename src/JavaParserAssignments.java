import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




public class JavaParserAssignments {
    public static void main(String [] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here

        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, String> assignmentMap = new HashMap();

        AssignmentVisitor visitor = new AssignmentVisitor();

        visitor.visit(cu, assignmentMap);

        for(Map.Entry<String, String> entry : assignmentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class AssignmentVisitor extends VoidVisitorAdapter<Map<String, String>> {
        @Override
        public void visit(AssignExpr n, Map<String, String> assignmentMap) {
            String target = n.getTarget().toString();
            String value = n.getValue().toString();

            assignmentMap.put(target, value);
            super.visit(n, assignmentMap);
        }
    }

}
