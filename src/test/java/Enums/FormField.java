package Enums;

import java.util.ArrayList;
import java.util.List;

public enum FormField {
    CITY("City"),
    STATE("State"),
    ZIP("Zip");

    private final String placeholder;

    FormField(String placeholder) {
        this.placeholder = placeholder;
    }

    public static String getPlaceholder(FormField field) {
        return field.placeholder;
    }

    public static List<FormField> getFormFieldsList() {
        return new ArrayList<>(List.of(FormField.values()));
    }
}
