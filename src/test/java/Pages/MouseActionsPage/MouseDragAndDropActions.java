package Pages.MouseActionsPage;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.ThreadLocalRandom;

public class MouseDragAndDropActions {

    private final WebDriver driver;

    public MouseDragAndDropActions(WebDriver driver) {
        this.driver = driver;
    }

    private final By dragSource = By.id("drag_source");
    private final By dragTarget = By.id("drop_target");
    private final By dragValidate = By.xpath("//div[@class='row align-items-center text-center']//h3");

    @Getter
    private final String expectedDefaultDragTargetText = "Target";
    @Getter
    private final String expectedValidDragTargetText = "Drop is successful!";

    public WebElement getDragSource() {
        return driver.findElement(dragSource);
    }

    public WebElement getDragTarget() {
        return driver.findElement(dragTarget);
    }

    public WebElement getDragValidate() {
        return driver.findElement(dragValidate);
    }

    public void dropItem() {
        new Actions(driver)
                .dragAndDrop(getDragSource(), getDragTarget())
                .build()
                .perform();
    }

    public boolean isDragAndDropSuccessful() {
        return getDragValidate().getText().equalsIgnoreCase(getExpectedValidDragTargetText());
    }
}
