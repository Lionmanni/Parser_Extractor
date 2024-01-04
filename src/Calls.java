import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Calls {
    public static void main(String[] args) throws IOException{
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here

        CompilationUnit cu = StaticJavaParser.parse(file);

        //Map<String, Integer> callsMap = new HashMap<>();
        Map<String,List<SimpleEntry<Integer, Integer>>> callsMap = new HashMap<>();

        CallVisitor visitor = new CallVisitor();

        visitor.visit(cu, callsMap);

        for(Map.Entry<String,List<SimpleEntry<Integer, Integer>>> entry : callsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    private static class CallVisitor extends VoidVisitorAdapter<Map<String,List<SimpleEntry<Integer, Integer>>>> {
        @Override
        public void visit(MethodCallExpr n, Map<String,List<SimpleEntry<Integer, Integer>>> callsMap) {
            String callName = n.getNameAsString();
            int startline = n.getBegin().map(pos -> pos.line).orElse(-1);
            int endline = n.getEnd().map(pos -> pos.line).orElse(-1);

            SimpleEntry<Integer, Integer> linePair = new SimpleEntry<>(startline, endline);
            callsMap.computeIfAbsent(callName, k -> new ArrayList<>()).add(linePair);



            super.visit(n, callsMap);
        }
    }
}
