import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
// The import below is unnecessary and potentially problematic, so it's recommended to remove it.
// import com.sun.xml.internal.ws.wsdl.writer.document.Import;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;


public class Imports {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Error: No file path provided.");
            return;
        }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here

        CompilationUnit cu = StaticJavaParser.parse(file);

        Map<String, Integer> importMap = new HashMap<>();

        ImportVisitor visitor = new ImportVisitor();

        visitor.visit(cu, importMap);
        for (Map.Entry<String, Integer> entry : importMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static class ImportVisitor extends VoidVisitorAdapter<Map<String, Integer>> {
        @Override
        public void visit(ImportDeclaration n, Map<String, Integer> importMap) {
            String importName = n.getNameAsString();
            int importLine = n.getBegin().isPresent() ? n.getBegin().get().line : -1;
            importMap.put(importName, importLine);
            super.visit(n, importMap);
        }

    }

}


