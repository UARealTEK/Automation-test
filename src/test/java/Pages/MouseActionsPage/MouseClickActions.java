package Pages.MouseActionsPage;

import org.openqa.selenium.*;

import java.util.HashMap;

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

    public Dimension getClickAreaDimension() {
        return getClickArea().getSize();
    }

    public Point getClickAreaLocation() {
        return getClickArea().getLocation();
    }
}
