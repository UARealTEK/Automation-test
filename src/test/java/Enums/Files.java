package Enums;

import Utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Files {
    WINDOWS_FILE_1("46.PDF"),
    WINDOWS_FILE_2("47.PDF"),
    MAC_FILE_1("Selenium Testing Tools Cookbook - 2012.pdf");

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
        return String.format(Constants.BASE_FILEPATH_DOWNLOADS_MAC + fileName);
    }

    public static String getFilePathWindows(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(Constants.BASE_FILEPATH_DOWNLOADS + fileName);
    }

    public static String getMultiFilePathsWindows(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(Constants.BASE_FILEPATH_DOWNLOADS + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }

    public static String getMultiFilePathsMac(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) {
                String fileNameToAdd = String.format(Constants.BASE_FILEPATH_DOWNLOADS_MAC + Files.getFileName(file.name()));
                builder.append(fileNameToAdd).append("\n");
            }

        }
        return builder.toString().trim();
    }
}
