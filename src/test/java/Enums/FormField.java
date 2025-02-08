package Enums;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum FormField {

    CITY,
    STATE,
    ZIP;

    public FormField getRandomField() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return getFormFieldsList().get(random.nextInt(getFormFieldsList().size()));
    }

    public static List<FormField> getFormFieldsList() {
        return new ArrayList<>(List.of(FormField.values()));
    }
}
