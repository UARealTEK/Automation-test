package Enums;

import Utils.Constants;
import Utils.DriverOperations;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Files {
    WINDOWS_FILE_1("sample_text.txt"),
    WINDOWS_FILE_2("sample_text (1).txt"),
    WINDOWS_FILE_3("sample_text (2).txt"),
    WINDOWS_FILE_4("sample_text (3).txt"),
    WINDOWS_FILE_5("sample_text (4).txt"),
    WINDOWS_FILE_6("sample_text (5).txt");

    private static final Logger log = LogManager.getLogger(Files.class);
    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public static List<Files> getFiles() {
        return Arrays.stream(Files.values()).toList();
    }

    public static String getFileName(String filename) {
        return Arrays.stream(Files.values())
                .filter(file -> file.name().equalsIgnoreCase(filename))
                .findFirst()
                .map(file -> file.fileName)
                .orElse(null);
    }

    public static String getFilePath(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(Constants.UPLOAD_DIRECTORY + "/" + fileName);
    }

    public static String getMultiFilePaths(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(Constants.UPLOAD_DIRECTORY + "/" + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }

    public static String getMultiFilePaths(List<Files> files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(Constants.UPLOAD_DIRECTORY + "/" + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }
}
