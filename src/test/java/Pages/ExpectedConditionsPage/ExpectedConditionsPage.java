package Pages.ExpectedConditionsPage;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class ExpectedConditionsPage {
    //Driver
    private final WebDriver driver;

    //Utils
    private static final Random random = new Random();

    //Controls
    private final By minWaitField = By.id("min_wait");
    private final By maxWaitField = By.id("max_wait");

    // Action Trigger buttons
    private final By showAlertButton = By.id("alert_trigger");
    private final By showPromptButton = By.id("prompt_trigger");
    private final By triggerElementVisibilityButton = By.id("visibility_trigger");
    private final By visibilityTargetElement = By.id("visibility_target");
    private final By triggerElementInVisibilityButton = By.id("invisibility_trigger");
    private final By invisibilityTargetElement = By.id("invisibility_target");
    private final By triggerElementEnablingButton = By.id("enabled_trigger");
    private final By enabledTargetElement = By.id("enabled_target");
    private final By titleChangeButton = By.id("page_title_trigger");
    private final By setFieldValueButton = By.id("text_value_trigger");
    private final By waitingForAValueField = By.id("wait_for_value");
    private final By waitingForAValueButton = By.id("wait_for_text");
    private final By waitForFrameButton = By.id("wait_for_frame");
    private final By frame = By.xpath("//div[@class='col-sm-5 align-items-center']//iframe");

    // Waits
    private WebDriverWait smallWait = null;
    private WebDriverWait longWait = null;

    //Constructor
    public ExpectedConditionsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Getter Methods
    public WebDriverWait getSmallWait() {
        setMinWait();
        return smallWait;
    }
    public WebDriverWait getLongWait() {
        setMaxWait();
        return longWait;
    }

    //Getters
    @Getter
    private final int minFieldValue = random.nextInt(1,11);
    @Getter
    private final int maxFieldValue = random.nextInt(1,11) + minFieldValue;
    @Getter
    private final String changedAttributeValue = "btn btn-success";
    @Getter
    private final String changedAttributeToFind = "class";
    @Getter
    private final String targetTitleName = "My New Title!";
    @Getter
    private final String targetFieldValue = "Dennis Ritchie";
    @Getter
    private final String targetButtonValue = "Submit";
    @Getter
    private final String targetFrameTitleText = "Inner Frame";

    //Setter Methods
    private void setMinWait() {
        smallWait = new WebDriverWait(driver, Duration.ofSeconds((long) (minFieldValue - 0.0005)));
    }

    private void setMaxWait() {
        longWait = new WebDriverWait(driver, Duration.ofSeconds(maxFieldValue - minFieldValue));
    }

    //Action setup method
    public void actionSetup() {
        WebElement minWaitElement = driver.findElement(minWaitField);
        minWaitElement.clear();
        minWaitElement.click();
        minWaitElement.sendKeys(String.valueOf(minFieldValue));

        WebElement maxWaitElement = driver.findElement(maxWaitField);
        maxWaitElement.clear();
        maxWaitElement.click();
        maxWaitElement.sendKeys(String.valueOf(maxFieldValue));
    }

    //Alert Methods
    public void showAlert() {
        actionSetup();
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
        actionSetup();
        driver.findElement(showPromptButton).click();
    }

    //Element Visibility Methods
    public void showElement() {
        actionSetup();
        driver.findElement(triggerElementVisibilityButton).click();
    }

    public WebElement getVisibilityTargetElement() {
        return driver.findElement(visibilityTargetElement);
    }

    //Element Invisibility Methods
    public void hideElement() {
        actionSetup();
        driver.findElement(triggerElementInVisibilityButton).click();
    }

    public WebElement getInVisibilityTargetElement() {
        return driver.findElement(invisibilityTargetElement);
    }

    //Element Enabling Methods
    public void enableElement() {
        actionSetup();
        driver.findElement(triggerElementEnablingButton).click();
    }

    public WebElement getEnabledElement() {
        return driver.findElement(enabledTargetElement);
    }

    //Title checks methods
    public void triggerTitleChange() {
        actionSetup();
        driver.findElement(titleChangeButton).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    //Button or field specific value checks
    public void triggerTextFieldSpecificValue() {
        actionSetup();
        driver.findElement(setFieldValueButton).click();
    }

    public WebElement getSpecificField() {
        return driver.findElement(waitingForAValueField);
    }

    public WebElement getSpecificButton() {
        return driver.findElement(waitingForAValueButton);
    }

    //Frame appearance methods
    public void triggerFrameAppearance() {
        actionSetup();
        driver.findElement(waitForFrameButton).click();
    }

    public By getFrameLocator() {
        return frame;
    }

    public String getFrameDocumentTitle() {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.title;");
    }

}
