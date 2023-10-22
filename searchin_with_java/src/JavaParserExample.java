import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaParserExample {

    public static void main(String[] args) {
          if (args.length < 1) {
              System.out.println("Error: No file path provided.");
              return;
          }
        String filePath = args[0];
        File file = new File(filePath); // initializing the 'file' variable here




        try {
            CompilationUnit cu = StaticJavaParser.parse(file);

            List<ElementInfo> elements = new ArrayList<>();

            // Visit and print the methods names and parameters
            cu.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                    super.visit(n, arg);
                    elements.add(new ElementInfo(n.getNameAsString(), "Class/Interface", "", n.getBegin().get().line, n.getEnd().get().line)); // Empty return type for classes/interfaces
                }

                @Override
                public void visit(MethodDeclaration n, Void arg) {
                    super.visit(n, arg);
                    ElementInfo methodInfo = new ElementInfo(n.getNameAsString(), "Method", n.getTypeAsString(), n.getBegin().get().line, n.getEnd().get().line);

                    for (Parameter parameter : n.getParameters()) {
                        String parameterName = parameter.getNameAsString();
                        String parameterType = parameter.getTypeAsString();
                        methodInfo.addParameter(parameterName, parameterType); // Ensure you have this method in your ElementInfo class.
                    }

                    elements.add(methodInfo);
                }
            }, null);

            // Print the information gathered
            for (ElementInfo element : elements) {
                System.out.println(element);
                // If ElementInfo class has a proper toString method, it will print meaningful information
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
