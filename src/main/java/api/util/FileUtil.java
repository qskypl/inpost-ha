package api.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    private FileUtil() {
    }

    public static void saveFile(String content, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
