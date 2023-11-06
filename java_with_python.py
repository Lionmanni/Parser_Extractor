import subprocess
import ast
import re


def main(classpath, java_class, file_path):
    # The command to run the Java program, ensure the classpath includes both
    # your external JARs and the directory containing your compiled classes.
    command = ['java', '-cp', classpath, java_class, file_path]

    # ... (rest of your function remains the same

    # Execute the command
    output = subprocess.check_output(command, stderr=subprocess.STDOUT)

    # Decode the output from bytes to string
    decoded_output = output.decode()

    return decoded_output


if __name__ == "__main__":

    # Define the classpath, including the path to the external JARs and your classes.
    # For example: "/path/to/javaparser-core-X.XX.X.jar:/path/to/compiled_classes"
    classpath = r"/home/andre/Downloads/javaparser-core-3.25.5.jar:/home/andre/Workspace/Albis_workflow/searchin_with_java/searchin_with_java/src"


    java_classes = ["Methods_Classes", "Calls", "Assignments", "Initializations", "Imports", "Strings","Variables"]
    # Possible Classnames : ['Methods_Classes', 'Calls', 'Assignments', 'Initializations', 'Imports', 'Strings','Variables'}

    # Define the path to the Java file you want to process
    file_path = r"/home/andre/Workspace/Albis Workflow/WfJavaServer (copy)/source/com/albis/hitec/workflow/server/WorkflowDruckeVUnterlagen.java"  # Replace with your file path



    for java_class in java_classes:
        output = main(classpath, java_class, file_path)
        print(f"Output from {java_class}:\n{output}\n")

    main(classpath, java_class, file_path)

    output = main(classpath, java_class, file_path)
    print(output)  # This will print the decoded_output to the terminal




    main(classpath, main_class, file_path)
