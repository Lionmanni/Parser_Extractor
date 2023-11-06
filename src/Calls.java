import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Calls {
    public static void main(String[] args) throws IOException{
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here

        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, Integer> callsMap = new HashMap<>();

        CallVisitor visitor = new CallVisitor();

        visitor.visit(cu, callsMap);

        for(Map.Entry<String, Integer> entry : callsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    private static class CallVisitor extends VoidVisitorAdapter<Map<String, Integer>> {
        @Override
        public void visit(MethodCallExpr n, Map<String, Integer> callsMap) {
            String callName = n.getNameAsString();
            int startLine = n.getBegin().map(pos -> pos.line).orElse(-1);
            callsMap.put(callName, startLine);
            super.visit(n, callsMap);
            //Was soll extracted werden ?
            //Calls und Startline
            //super.visit und put to HasMap
        }
    }
}
