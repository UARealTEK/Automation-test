package Pages.FormsPage.BasicFormControls.ToggleSwitch;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class ToggleSwitch {

    private final WebDriver driver;

    public ToggleSwitch(WebDriver driver) {
        this.driver = driver;
    }

    //Switch checkbox
    private final By switchBoxButton = By.id("german");

    //Switch checkbox Label
    private final By switchBoxLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='german']");
    private final By switchBoxValidate = By.id("german_validate");

    //Toggle switch methods
    public WebElement getToggleSwitch() {
        return driver.findElement(switchBoxButton);
    }

    public WebElement getToggleSwitchLabelElement() {
        return driver.findElement(switchBoxLabel);
    }

    public boolean isToggleSwitchChecked() {
        return Optional.ofNullable(getToggleSwitch().getAttribute("checked")).isPresent();
    }

    public String getToggleSwitchValidateLabel() {
        return driver.findElement(switchBoxValidate).getText();
    }

    public boolean isToggleSwitchLabelMatched() {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        Object firstInteraction = js.executeScript("return window.firstInteractionDone;");
        boolean isSelected = getToggleSwitch().isSelected();
        String labelValue = getToggleSwitchValidateLabel();
        Optional<String> toggleStateValue = Optional.ofNullable(getToggleSwitch().getAttribute("checked"));

        if (firstInteraction == null && !isSelected && toggleStateValue.isEmpty()) {
            return labelValue.isEmpty();
        } else if (toggleStateValue.isPresent() && isSelected) {
            return labelValue.equals(toggleStateValue.get());
        }

        return false;
    }
}
