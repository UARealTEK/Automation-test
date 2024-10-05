package WaitConditions.Helper;

import Enums.URLs;
import Utils.BaseOperations;
import org.openqa.selenium.WebElement;

import java.net.URL;

public class ElementVisibilityBaseOperations extends BaseOperations {

    public static void triggerElementToSee(int minWait, int maxWait) {
        BaseOperations.navigateTo(URLs.WAIT_CONDITIONS);

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
        BaseOperations.navigateTo(URLs.WAIT_CONDITIONS);

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
