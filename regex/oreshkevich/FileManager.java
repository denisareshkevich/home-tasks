package regex.oreshkevich;

import java.io.*;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class FileManager {

    public static String readText(String filename) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        }
    }

    public static void writeText(String text, String filename) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {
            writer.write(text);
        }

    }

}
