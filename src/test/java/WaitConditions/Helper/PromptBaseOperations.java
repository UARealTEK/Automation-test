package WaitConditions.Helper;

import Utils.BaseOperations;
import org.openqa.selenium.WebElement;

public class PromptBaseOperations extends BaseOperations {

    public static void showPrompt(int minWait, int maxWait) {
        BaseOperations.openPage("https://play1.automationcamp.ir/expected_conditions.html");

        WebElement setMinWaitField = BaseOperations.locateElementBy("//*[@id=\"min_wait\"]","xpath");
        WebElement setMaxWaitField = BaseOperations.locateElementBy("//*[@id=\"max_wait\"]","xpath");
        WebElement showPromptButton = BaseOperations.locateElementBy("//*[@id=\"prompt_trigger\"]","xpath");

        BaseOperations.clickElement(setMinWaitField);
        setMinWaitField.clear();
        setMinWaitField.sendKeys(String.valueOf(minWait));

        BaseOperations.clickElement(setMaxWaitField);
        setMaxWaitField.clear();
        setMaxWaitField.sendKeys(String.valueOf(maxWait));

        showPromptButton.click();
    }
}
