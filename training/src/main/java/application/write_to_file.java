package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class write_to_file {

    // The name of the file to open.
    public static void write() {
        
        String fileName = "database.txt";
        
        // Always wrap FileWriter in BufferedWriter.
        // try-with-resources Java 7 feature : this will always close Closeable objects 
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) { 
            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("Hello there,");
            bufferedWriter.write(" here is some text.");
            bufferedWriter.newLine();
            bufferedWriter.write("We are writing");
            bufferedWriter.write(" the text to the file.");

        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
