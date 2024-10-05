package Pages.ExpectedConditions;

import org.openqa.selenium.By;

public class ExpectedConditionsPage {
    //Controls
    private By minWait = By.xpath("//div[span[@class='input-group-text' and text()='Min. Wait']]");
    private By maxWait = By.xpath("//div[span[@class='input-group-text' and text()='Max. Wait']]");
    private By minWaitField = By.id("min_wait");
    private By maxWaitField = By.id("max_wait");

    // Action Trigger buttons
    private By showAlertButton;
    private By showPromptButton;
    private By triggerElementVisibilityButton;
    private By triggerElementInVisibilityButton;
    private By triggerElementEnablingButton;
    private By disabledButton;
}
