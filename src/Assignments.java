import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;



public class Assignments {
    public static void main(String [] args) throws IOException {
        if (args.length < 1) {
        System.out.println("Error: No file path provided.");
        return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here

        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, object_def> assignmentMap = new HashMap();

        AssignmentVisitor visitor = new AssignmentVisitor();

        visitor.visit(cu, assignmentMap);

        for(Map.Entry<String, object_def> entry : assignmentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class AssignmentVisitor extends VoidVisitorAdapter<Map<String, object_def>> {
        @Override
        public void visit(AssignExpr n, Map<String, object_def> assignmentMap) {
            String target = n.getTarget().toString();
            String value = n.getValue().toString();


            int startline = n.getBegin().map(pos -> pos.line).orElse(-1);
            int endline = n.getEnd().map(pos -> pos.line).orElse(-1);

            object_def customValue = assignmentMap.getOrDefault(target, new object_def(value, new ArrayList<>()));
            customValue.getPairList().add(new SimpleEntry<>(startline, endline));
            assignmentMap.put(target, customValue);
            super.visit(n, assignmentMap);
        }
    }

}