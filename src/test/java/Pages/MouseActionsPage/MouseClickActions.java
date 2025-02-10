package Pages.MouseActionsPage;

import Enums.ClickType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.ThreadLocalRandom;

public class MouseClickActions {

    private final WebDriver driver;

    public MouseClickActions(WebDriver driver) {
        this.driver = driver;
    }

    private final By clickArea = By.id("click_area");
    private final By clickTypeVerify = By.id("click_type");
    private final By xCoordinateLabel = By.id("click_x");
    private final By yCoordinateLabel = By.id("click_y");

    public WebElement getClickArea() {
        return driver.findElement(clickArea);
    }

    public WebElement getClickTypeVerify() {
        return driver.findElement(clickTypeVerify);
    }

    public WebElement getXCoordinateLabel() {
        return driver.findElement(xCoordinateLabel);
    }

    public WebElement getYCoordinateLabel() {
        return driver.findElement(yCoordinateLabel);
    }

    private Integer extractCoordinateValue(String labelText, int startIndex) {
        return labelText.length() >= startIndex ? Integer.valueOf(labelText.substring(startIndex)) : null;
    }

    public Integer getExpectedClickAreaLabelValueTop() {
        return extractCoordinateValue(getYCoordinateLabel().getText(), 5);
    }

    public Integer getExpectedClickAreaLabelValueLeft() {
        return extractCoordinateValue(getXCoordinateLabel().getText(), 6);
    }

    public Dimension getClickAreaDimension() {
        return getClickArea().getSize();
    }

    public Point getClickAreaLocation() {
        return getClickArea().getLocation();
    }

    public Point getRandomOffset() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new Point(random.nextInt(getClickAreaDimension().getWidth()), random.nextInt(getClickAreaDimension().getHeight()));
    }

    public void clickClickAreaCoordinates(Point offsets, ClickType type) {
        Actions actions = new Actions(driver)
                .moveToLocation(getClickAreaLocation().getX() + offsets.getX() + 1,
                        getClickAreaLocation().getY() + offsets.getY() + 1);
        switch (type) {
            case CLICK -> actions.click();
            case DOUBLE_CLICK -> actions.doubleClick();
            case CONTEXT_CLICK -> actions.contextClick();
        }
        actions.build().perform();
    }


    public boolean isClickTypeMatched(ClickType clickTypePerformed) {
        String clickTypeValue = clickTypePerformed.getType();
        if (clickTypeValue.isEmpty()) {
            return getClickTypeVerify().getText().isEmpty();
        } else {
            return clickTypeValue.equalsIgnoreCase(getClickTypeVerify().getText());
        }
    }

    public boolean isClickTypeMatched() {
        return getClickTypeVerify().getText().isEmpty()
                && getExpectedClickAreaLabelValueLeft() == null
                && getExpectedClickAreaLabelValueTop() == null;
    }
}
