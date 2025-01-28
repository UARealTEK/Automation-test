package Pages.FormsPage.DisabledField;

import Utils.BaseOperations;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Optional;

public class DisabledField {

    private static final Logger log = LogManager.getLogger(DisabledField.class);
    private final WebDriver driver;

    //Disabled field
    private final By disabledTextBox = By.id("salary");

    //Disabled field Label
    private final By disabledTextBoxLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='salary']");
    @Getter
    private final String expectedDisabledTextBoxPlaceholder = "You should not provide this";
    @Getter
    private final String expectedDisabledTextBoxBackgroundColor = "rgba(233, 236, 239, 1)";

    public DisabledField(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDisabledTextBox() {
        return driver.findElement(disabledTextBox);
    }

    public WebElement getDisabledTextBoxLabel() {
        return driver.findElement(disabledTextBoxLabel);
    }

    private String getDisabledTextBoxID() {
        return getDisabledTextBox().getAttribute("id");
    }

    private String getDisabledTextBoxLabelFor() {
        return getDisabledTextBoxLabel().getAttribute("for");
    }

    public boolean isBackgroundColorMatched() {
        return getExpectedDisabledTextBoxBackgroundColor()
                .equals(getDisabledTextBox().getCssValue("background-color"));
    }

    public boolean isTextBoxAndLabelMatched() {
        return getDisabledTextBoxID().equalsIgnoreCase(getDisabledTextBoxLabelFor());
    }

    public boolean isFieldDisabled() {
        return getDisabledTextBox().getAttribute("disabled") != null && !getDisabledTextBox().isEnabled();
    }

    public String getDisabledTextBoxPlaceholder() {
        return getDisabledTextBox().getAttribute("placeholder");
    }

    public boolean isPlaceholderMatched() {
        return getDisabledTextBoxPlaceholder().equalsIgnoreCase(getExpectedDisabledTextBoxPlaceholder());
    }

    public boolean isDisabledTextBoxFocused() {
        log.debug(getDisabledTextBox());
        log.debug(driver.switchTo().activeElement());
        getDisabledTextBox().click();
        return getDisabledTextBox().equals(driver.switchTo().activeElement());
    }

    public void sendKeysToDisabledTextBox() {
        Actions action = new Actions(driver);
        action.click(getDisabledTextBox()).sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()));
    }

    public boolean isDisabledTextBoxEmpty() {
        Optional<String> boxValue = Optional.ofNullable(getDisabledTextBox().getAttribute("value"));
        return boxValue
                .map(s -> s.isEmpty() && getDisabledTextBox().getText().isEmpty())
                .orElseGet(() -> getDisabledTextBox().getText().isEmpty());
    }




}
