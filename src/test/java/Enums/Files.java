package Enums;

import java.util.Arrays;

public enum Files {
    FILE_1("46.PDF"),
    FILE_2("47.PDF");

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
}
