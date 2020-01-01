package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
	
    public static void createWrite(String tt, String fileNam) {
        File f = new File(fileNam);
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    throw new RuntimeException("Cant create a file");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                //System.out.println("Sth wrong when setting gpo");
            }
        }
        try {
            Files.write(f.toPath(), tt.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void updateLastLine(String fileNam) {
        File f = new File(fileNam);
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    throw new RuntimeException("Cant create a file");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                //System.out.println("Sth wrong when setting gpo");
            }
        }
        try {
            List<String> lines = Files.readAllLines(f.toPath());
            int size = lines.size();
            for (int i = 0; i < size; ++i) {
                String lineToWrite = lines.get(i);
                if (i == size - 1) {
                    lineToWrite = lineToWrite.replaceFirst("GOOD", "ABNORMAL_HEADER");
                }
                Files.write(f.toPath(), lineToWrite.getBytes(), StandardOpenOption.WRITE);
            }
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

