package WaitConditions.Helper;

import Utils.BaseOperations;
import org.openqa.selenium.WebElement;

public class ElementVisibilityBaseOperations extends BaseOperations {

    public static void triggerElementToSee(int minWait, int maxWait) {
        BaseOperations.openPage("https://play1.automationcamp.ir/expected_conditions.html");

        WebElement setMinWaitField = BaseOperations.locateElementBy("//*[@id=\"min_wait\"]","xpath");
        WebElement setMaxWaitField = BaseOperations.locateElementBy("//*[@id=\"max_wait\"]","xpath");
        WebElement triggerButton = BaseOperations.locateElementBy("//*[@id=\"visibility_trigger\"]","xpath");

        BaseOperations.clickElement(setMinWaitField);
        setMinWaitField.clear();
        setMinWaitField.sendKeys(String.valueOf(minWait));

        BaseOperations.clickElement(setMaxWaitField);
        setMaxWaitField.clear();
        setMaxWaitField.sendKeys(String.valueOf(maxWait));

        triggerButton.click();
    }

    public static void triggerElementToUnSee(int minWait, int maxWait) {
        BaseOperations.openPage("https://play1.automationcamp.ir/expected_conditions.html");

        WebElement setMinWaitField = BaseOperations.locateElementBy("//*[@id=\"min_wait\"]","xpath");
        WebElement setMaxWaitField = BaseOperations.locateElementBy("//*[@id=\"max_wait\"]","xpath");
        WebElement triggerButton = BaseOperations.locateElementBy("//*[@id=\"invisibility_trigger\"]","xpath");

        BaseOperations.clickElement(setMinWaitField);
        setMinWaitField.clear();
        setMinWaitField.sendKeys(String.valueOf(minWait));

        BaseOperations.clickElement(setMaxWaitField);
        setMaxWaitField.clear();
        setMaxWaitField.sendKeys(String.valueOf(maxWait));

        triggerButton.click();
    }

}
