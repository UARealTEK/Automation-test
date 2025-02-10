package Enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum ClickType {
    CLICK("Click"),
    DOUBLE_CLICK("Double-Click"),
    CONTEXT_CLICK("Right-Click");

    private final String type;

    ClickType(String type) {
        this.type = type;
    }

    public static List<ClickType> getAllClickTypes() {
        return Arrays.stream(ClickType.values()).toList();
    }

    public static ClickType getRandomClickType() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return getAllClickTypes().get(random.nextInt(getAllClickTypes().size()));
    }
}
