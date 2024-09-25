package WaitConditions.AlertsTests;

import SamplePages.helperClasses.BaseOperations;
import SamplePages.helperClasses.TestUtils;
import WaitConditions.helperClasses.AlertsBaseOperations;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsUITests extends BaseOperations {

    @Test
    public void checkAlertData() {
        AlertsBaseOperations.showAlert(0,2); // Assuming the alert will be displayed in the first 2 seconds

        Alert alert = BaseOperations.getWait().until(ExpectedConditions.alertIsPresent());
        BaseOperations.getDriver().switchTo().alert();

        assertEquals("I am alerting you!",
                alert.getText(),
                String.format("Incorrect alert! Im expecting 'I am alerting you' but got %s",
                        alert.getText()));
        alert.accept();
    }

    @AfterAll
    public static void tearDown() {
        TestUtils.quitWebDriver();
    }
}