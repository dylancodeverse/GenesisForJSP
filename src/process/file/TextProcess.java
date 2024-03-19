package process.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextProcess {
    public static void writeTextToFile(String text, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
            System.out.println("Text has been written to the file successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            throw e;
        }
    }
}
