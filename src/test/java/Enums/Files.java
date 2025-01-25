package Enums;

import Utils.Constants;

import java.util.Arrays;

public enum Files {
    FILE_1("46.PDF"),
    FILE_2("47.PDF"),
    FILE_3("Selenium Testing Tools Cookbook - 2012.pdf");

    private final String name;

    Files(String name) {
        this.name = name;
    }

    public static String getFileName(String filename) {
        return Arrays.stream(Files.values())
                .filter(file -> file.name()
                        .equalsIgnoreCase(filename))
                .findFirst().get().name;
    }

    public static String getFilePath(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(Constants.BASE_FILEPATH_DOWNLOADS_MAC + fileName);
    }

    /**
     * TODO:
     * complete this method
     */
    public static String getMultipleFilePaths(Files... files) {
        StringBuilder builder = new StringBuilder();

        for (Files file : files) {
            if (file != null) { // Ensure the file is not null and exists
//                builder.append(file.).append("\n");
            }

        }
        return builder.toString().trim();
    }
}
