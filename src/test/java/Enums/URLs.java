package Enums;

import java.util.Arrays;

public enum URLs {

    ORDER_PAGE("order_submit.html"),
    LOGIN_PAGE("login.html"),
    REGISTER_PAGE("register.html"),
    SIGN_UP_CONFIRMATION("confirmation.html"),
    EXPECTED_CONDITIONS("expected_conditions.html"),
    FORMS_PAGE("forms.html"),
    MOUSE_EVENTS("mouse_events.html");

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
