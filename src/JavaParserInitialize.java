
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JavaParserInitialize {
    public static void main(String[] args) throws IOException{
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here
        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, Integer> initMap = new HashMap<>();

        InitVisitor visitor = new InitVisitor();

        visitor.visit(cu, initMap);

        for(Map.Entry<String, Integer> entry : initMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    private static class InitVisitor extends VoidVisitorAdapter<Map<String, Integer>> {
        @Override
        public void visit(VariableDeclarator n, Map<String, Integer> initMap) {
            String initName = n.getNameAsString();
            int startLine = n.getBegin().map(pos -> pos.line).orElse(-1);
            initMap.put(initName, startLine);
            super.visit(n, initMap);
            //Was soll extracted werden ?
            //Calls und Startline
            //super.visit und put to HasMap
        }
    }
}
