import subprocess
import os

def parse_java_output(output):
    """
    Parses the provided Java program output and creates a list of dictionaries based on the parsed content.
    """
    # Split the output by line and then process each line
    lines = output.strip().split("\n")

    parsed_data = []
    for line in lines:
        segments = line.split(", ")
        info_dict = {}

        for segment in segments:
            # Split by the first colon to separate the key and value
            parts = segment.split(": ", 1)
            if len(parts) == 2:
                key, value = parts
                info_dict[key.strip()] = value.strip()

        if info_dict:
            parsed_data.append(info_dict)

    return parsed_data

def main(classpath, main_class, file_path):
    # The command to run the Java program, ensure the classpath includes both
    # your external JARs and the directory containing your compiled classes.
    command = ['java', '-cp', classpath, main_class, file_path]

    # ... (rest of your function remains the same
    try:
        # Execute the command
        output = subprocess.check_output(command, stderr=subprocess.STDOUT)

        # Decode the output from bytes to string
        decoded_output = output.decode()

        # Now, we use the parsing function to process the Java output
        parsed_data = parse_java_output(decoded_output)

        # Print or return the parsed data, depending on your needs
        print(parsed_data)

    except subprocess.CalledProcessError as e:
        # Handle exceptions thrown due to non-zero exit status
        print(f"An error occurred while trying to run the command: {e}")
        print(f"Output: {e.output.decode()}")
    except Exception as e:
        # Handle other exceptions
        print(f"An unexpected error occurred: {e}")

if __name__ == "__main__":
    # Define the classpath, including the path to the external JARs and your classes.
    classpath = "C:\\Users\\Andre Mannweiler\\Desktop\\Lion_Arbeit\\Roman_Albis\\Parser_Extractor-main\\searchin_with_java\\javaparser-core-3.25.5.jar;C:\\Users\\Andre Mannweiler\\Desktop\\Lion_Arbeit\\Roman_Albis\\Parser_Extractor-main\\searchin_with_java\\src"

    file_path = "C:\\Users\\Andre Mannweiler\\Downloads\\AbrechnungPanel.java"

    # Define the main class you want to execute
    main_class = "JavaParserExample"  # replace with your actual main class name

    main(classpath, main_class, file_path)
