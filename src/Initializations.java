
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;


public class Initializations {
    public static void main(String[] args) throws IOException{
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here
        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String,List<AbstractMap.SimpleEntry<Integer, Integer>>> initMap = new HashMap<>();


        InitVisitor visitor = new InitVisitor();

        visitor.visit(cu, initMap);

        for(Map.Entry<String,List<AbstractMap.SimpleEntry<Integer, Integer>>> entry : initMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    private static class InitVisitor extends VoidVisitorAdapter<Map<String,List<AbstractMap.SimpleEntry<Integer, Integer>>>> {
        @Override
        public void visit(VariableDeclarator n, Map<String,List<AbstractMap.SimpleEntry<Integer, Integer>>> initMap) {
            String initName = n.getNameAsString();
            int startline = n.getBegin().map(pos -> pos.line).orElse(-1);
            int endline = n.getEnd().map(pos -> pos.line).orElse(-1);

            AbstractMap.SimpleEntry<Integer, Integer> linePair = new AbstractMap.SimpleEntry<>(startline, endline);
            initMap.computeIfAbsent(initName, k -> new ArrayList<>()).add(linePair);

            super.visit(n, initMap);

        }
    }
}
