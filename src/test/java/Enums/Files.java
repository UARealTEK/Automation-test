package Enums;

import Utils.Constants;
import Utils.DriverOperations;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Files {
    WINDOWS_FILE_1("sample_text.txt"),
    WINDOWS_FILE_2("sample_text (1).txt"),
    WINDOWS_FILE_3("sample_text (2).txt"),
    WINDOWS_FILE_4("sample_text (3).txt"),
    WINDOWS_FILE_5("sample_text (4).txt"),
    WINDOWS_FILE_6("sample_text (5).txt"),
    MAC_FILE_1("1.pdf"),
    MAC_FILE_2("2.pdf"),
    MAC_FILE_3("3.pdf"),
    MAC_FILE_4("4.pdf"),
    MAC_FILE_5("5.pdf"),
    MAC_FILE_6("6.pdf");

    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public static List<Files> getMacFiles() {
        return Arrays.stream(Files.values()).filter(file -> file.name().startsWith("MAC")).toList();
    }

    public static List<Files> getWindowsFiles() {
        return Arrays.stream(Files.values()).filter(file -> file.name().startsWith("WINDOWS")).toList();
    }

    public static String getFileName(String filename) {
        return Arrays.stream(Files.values())
                .filter(file -> file.name().equalsIgnoreCase(filename))
                .findFirst()
                .map(file -> file.fileName)
                .orElse(null);
    }


    public static String getFilePathMac(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(DriverOperations.getDownloadDirectory() + fileName);
    }

    public static String getFilePathWindows(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(DriverOperations.getDownloadDirectory() + fileName);
    }

    public static String getMultiFilePathsWindows(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(DriverOperations.getDownloadDirectory() + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }

    public static String getMultiFilePathsWindows(List<Files> files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(DriverOperations.getDownloadDirectory() + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }

    public static String getMultiFilePathsMac(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(DriverOperations.getDownloadDirectory() + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }

    public static String getMultiFilePathsMac(List<Files> files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(DriverOperations.getDownloadDirectory() + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }
}
