package Enums;

import java.util.Arrays;

public enum URLs {

    ORDER_PAGE("order_submit.html"),
    LOGIN_PAGE("login.html"),
    REGISTER_PAGE("register.html"),
    SIGN_UP_CONFIRMATION("confirmation.html"),
    WAIT_CONDITIONS("expected_conditions.html");

    private String endpoint;

    URLs(String endpoint) {
        this.endpoint = endpoint;
    }

    public static String getURLValue(String name) {
        return Arrays.stream(URLs.values())
                .filter(url -> url.name().equalsIgnoreCase(name))
                .findFirst()
                .get().endpoint;
    }
}
