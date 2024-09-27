package WaitConditions.AlertsTests;
import SamplePages.Helper.BaseOperations;
import SamplePages.Helper.BaseTest;
import WaitConditions.Helper.AlertsBaseOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class AlertsFunctionalTests extends BaseTest {

    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isDisplayedAlertAfterDelay(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> isDisplayedAlertAfterDelay(7,10)));
    }


    public void isDisplayedAlertAfterDelay(int min, int max) {
        SoftAssertions soft = new SoftAssertions();
        AlertsBaseOperations.showAlert(min, max); // Assuming these are seconds
        boolean alertIsDisplayed = false;

        WebDriverWait shortWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds((long) (min - 0.1)));
        try {
            shortWait.until(ExpectedConditions.alertIsPresent());
            alertIsDisplayed = true;
        } catch (Exception e) {

        }

        soft.assertThat(alertIsDisplayed)
                .as(String.format("alert was displayed in the first %s seconds", min))
                .isFalse();

        // Max delay
        WebDriverWait longWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds((max - min)));

        try {
            Alert alert = longWait.until(ExpectedConditions.alertIsPresent());
            alertIsDisplayed = true;
            alert.accept(); // Закриває алерт
        } catch (Exception e) {

        }

        soft.assertThat(alertIsDisplayed)
                .as(String.format("Alert was displayed in the window between %s and %s seconds", min, max))
                .isTrue();
    }
}
