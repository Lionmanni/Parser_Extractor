import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JavaParserStrings {
        public static void main(String[] args) throws IOException {
            if (args.length < 1) {
                System.out.println("Error: No file path provided.");
                return;
            }
            String filePath = args[0];
            File file = new File(filePath); // initializing the 'file' variable here
            CompilationUnit cu = StaticJavaParser.parse(file);

            Map<String, Integer> stringMap = new HashMap<>();

            StringVisitor visitor = new StringVisitor();

            visitor.visit(cu, stringMap);
            for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        private static class StringVisitor extends VoidVisitorAdapter<Map<String, Integer>> {
            @Override
            public void visit(StringLiteralExpr n, Map<String, Integer> stringMap) {
                String stringContent = n.asString();
                int stringLine = n.getBegin().isPresent() ? n.getBegin().get().line : -1;
                stringMap.put(stringContent, stringLine);
                super.visit(n, stringMap);
            }

        }

}
