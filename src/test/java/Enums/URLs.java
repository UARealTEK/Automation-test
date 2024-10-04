package Enums;

import java.util.Arrays;

public enum URLs {

    ORDER_PAGE("order_submit.html"),
    LOGIN_PAGE("login.html"),
    REGISTER_PAGE("register.html");

    private String fullName;

    URLs(String fullName) {
        this.fullName = fullName;
    }

    public static String getURLValue(String name) {
        return Arrays.stream(URLs.values())
                .filter(url -> url.name().equalsIgnoreCase(name))
                .findFirst()
                .get().fullName;
    }
}
