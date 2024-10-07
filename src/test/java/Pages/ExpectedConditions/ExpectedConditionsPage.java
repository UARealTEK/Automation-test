package Pages.ExpectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class ExpectedConditionsPage {
    //Driver
    private WebDriver driver;

    //Utils
    private Random random = new Random();

    //Controls
    private By minWaitField = By.id("min_wait");
    private By maxWaitField = By.id("max_wait");
    private int minFieldValue = random.nextInt(1,11);
    private int maxFieldValue = random.nextInt(1,11) + minFieldValue;

    // Action Trigger buttons
    private By showAlertButton = By.id("alert_trigger");
    private By showPromptButton = By.id("prompt_trigger");
    private By triggerElementVisibilityButton = By.id("visibility_trigger");
    private By triggerElementInVisibilityButton = By.id("invisibility_trigger");
    private By triggerElementEnablingButton = By.id("enabled_trigger");
    private By disabledButton = By.id("enabled_target");
    private By titleChangeButton = By.id("page_title_trigger");
    private By setFieldValueButton = By.id("text_value_trigger");
    private By waitingForAValueField = By.id("wait_for_value");
    private By getWaitingForAValueButton = By.id("wait_for_text");
    private By waitForFrameButton = By.id("wait_for_frame");

    // Waits
    private WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds((long) (minFieldValue - 0.0001)));
    private WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(maxFieldValue - minFieldValue));

    //Constructor
    public ExpectedConditionsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Getter Methods
    public WebDriverWait getSmallWait() {
        return smallWait;
    }

    public WebDriverWait getLongWait() {
        return longWait;
    }
    public int getMinFieldValue() {
        return minFieldValue;
    }
    public int getMaxFieldValue() {
        return maxFieldValue;
    }

    //Alert Methods
    public void showAlert() {
        WebElement minWaitElement = driver.findElement(minWaitField);
        minWaitElement.clear();
        minWaitElement.click();
        minWaitElement.sendKeys(String.valueOf(minFieldValue));

        WebElement maxWaitElement = driver.findElement(maxWaitField);
        maxWaitElement.clear();
        maxWaitElement.click();
        maxWaitElement.sendKeys(String.valueOf(maxFieldValue));

        driver.findElement(showAlertButton).click();
    }

    public boolean isAlertDisplayed() {
        try {
            driver.switchTo().alert(); // Switch to alert
            return true; // Alert is present
        } catch (NoAlertPresentException e) {
            return false; // No alert found
        }
    }

    //Prompt Methods
    public void showPrompt() {
        WebElement minWaitElement = driver.findElement(minWaitField);
        minWaitElement.clear();
        minWaitElement.click();
        minWaitElement.sendKeys(String.valueOf(minFieldValue));

        WebElement maxWaitElement = driver.findElement(maxWaitField);
        maxWaitElement.clear();
        maxWaitElement.click();
        maxWaitElement.sendKeys(String.valueOf(maxFieldValue));

        driver.findElement(showPromptButton).click();
    }
}
