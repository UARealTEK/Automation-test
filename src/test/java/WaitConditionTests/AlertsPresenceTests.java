package WaitConditionTests;

import SamplePagesTests.helperClasses.BaseOperations;
import SamplePagesTests.helperClasses.TestUtils;
import WaitConditionTests.helperClasses.AlertsBaseOperations;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsPresenceTests extends BaseOperations {
    private static final long minWait = System.currentTimeMillis(); // starting point
    private static final long maxWait = minWait + 5000; // maximum wait time of 5 seconds

    @Test
    public void isDisplayedAlertAfterDelay() {
        AlertsBaseOperations.showAlert(2, 5); // Assuming these are seconds
        boolean alertIsAppeared = false;

        // Start checking for the alert after 2 seconds
        long checkStartTime = minWait + 2000;

        while (System.currentTimeMillis() < maxWait) {
            if (System.currentTimeMillis() >= checkStartTime) {
                try {
                    WebDriverWait waitForAlert = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(5));
                    waitForAlert.until(ExpectedConditions.alertIsPresent());
                    alertIsAppeared = true;
                    Alert alert = BaseOperations.getDriver().switchTo().alert();
                    alert.accept();
                    break; // Exit loop if alert is found
                } catch (NoAlertPresentException e) {
                    // Alert not present, continue to wait
                }
            }
        }

        Assert.assertTrue(alertIsAppeared, "Alert was NOT displayed");
    }

    @AfterClass
    public void tearDown() {
        TestUtils.quitWebDriver();
    }

}
