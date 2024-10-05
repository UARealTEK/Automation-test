package WaitConditions.Helper;

import Enums.URLs;
import Utils.BaseOperations;
import org.openqa.selenium.WebElement;

public class AlertsBaseOperations extends BaseOperations {

    public static void showAlert(long minWait, long maxWait) {
        BaseOperations.navigateTo(URLs.WAIT_CONDITIONS);

        WebElement setMinWaitField = BaseOperations.locateElementBy("//*[@id=\"min_wait\"]","xpath");
        WebElement setMaxWaitField = BaseOperations.locateElementBy("//*[@id=\"max_wait\"]","xpath");
        WebElement showAlertButton = BaseOperations.locateElementBy("//*[@id=\"alert_trigger\"]","xpath");

        BaseOperations.clickElement(setMinWaitField);
        setMinWaitField.clear();
        setMinWaitField.sendKeys(String.valueOf(minWait));

        BaseOperations.clickElement(setMaxWaitField);
        setMaxWaitField.clear();
        setMaxWaitField.sendKeys(String.valueOf(maxWait));

        showAlertButton.click();
    }
}
