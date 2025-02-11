package Pages.MouseActionsPage;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MouseHoverActions {

    private final WebDriver driver;

    public MouseHoverActions(WebDriver driver) {
        this.driver = driver;
    }

    private final By button = By.xpath("//button[@class='dropbtn']");
    private final By javaOption = By.id("dd_java");
    private final By pythonOption = By.id("dd_python");
    private final By javaScriptOption = By.id("dd_javascript");
    private final By optionValidate = By.id("hover_validate");
    private final By options = By.xpath("//div[@class='dropdown-content']//p");

    public WebElement getButton() {
        return driver.findElement(button);
    }

    public WebElement getJavaOption() {
        return driver.findElement(javaOption);
    }

    public WebElement getPythonOption() {
        return driver.findElement(pythonOption);
    }

    public WebElement getJavaScriptOption() {
        return driver.findElement(javaScriptOption);
    }

    public WebElement getOptionValidate() {
        return driver.findElement(optionValidate);
    }

    public List<WebElement> getOptions() {
        return driver.findElements(options);
    }

    public void hoverOnButton() {
        new Actions(driver).moveToElement(getButton()).build().perform();
        BaseOperations.getWait()
                .until((ExpectedCondition<Boolean>) wd -> {
                    try {
                        List<WebElement> options = getOptions();
                        return options.stream().allMatch(WebElement::isDisplayed);
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                });
    }

    public void hoverOffButton() {
        new Actions(driver).moveToElement(getOptionValidate()).perform();
    }

    public boolean isAllElementsDisplayed() {
        return getOptions().stream().allMatch(WebElement::isDisplayed);
    }

    public void selectRandomOption(WebElement optionToSelect) {
        new Actions(driver).moveToElement(optionToSelect).click().perform();
    }

    public boolean isOptionAndLabelMatched(WebElement option) {
        return option.getText().equalsIgnoreCase(getOptionValidate().getText());
    }
}
