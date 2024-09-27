package WaitConditions.PromptsTests;

import SamplePages.Helper.BaseOperations;
import SamplePages.Helper.BaseTest;
import WaitConditions.Helper.PromptBaseOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PromptsFunctionalTests extends BaseTest {

    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isDisplayedPromptAfterDelay(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> isDisplayedPromptAfterDelay(7,10)));
    }

    public void isDisplayedPromptAfterDelay(int min, int max) {
        SoftAssertions soft = new SoftAssertions();
        PromptBaseOperations.showPrompt(min,max);

        boolean promptIsDisplayed = false;
        WebDriverWait minWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds((long) (min - 0.0001)));

        try {
            minWait.until(ExpectedConditions.alertIsPresent());
            promptIsDisplayed = true;
        } catch (Exception e) {
            //
        }
        soft.assertThat(promptIsDisplayed)
                .as(String.format("Prompt was displayed before %s seconds passed", min))
                .isFalse();


        WebDriverWait maxWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(max - min));

        try {
            Alert alert = maxWait.until(ExpectedConditions.alertIsPresent());
            promptIsDisplayed = true;
            alert.accept();
        } catch (Exception e) {
            //
        }

        soft.assertThat(promptIsDisplayed)
                .as(String.format("Alert was not displayed in the given period from %s until %s", min, max))
                .isTrue();
    }

}
