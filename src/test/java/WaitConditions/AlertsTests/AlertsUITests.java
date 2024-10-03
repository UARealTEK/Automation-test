package WaitConditions.AlertsTests;
import Utils.BaseOperations;
import WaitConditions.Helper.AlertsBaseOperations;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsUITests extends BaseOperations {

    @Test
    public void checkAlertData() {
        AlertsBaseOperations.showAlert(0,2); // Assuming the alert will be displayed in the first 2 seconds

        WebDriverWait alertWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(2));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
        BaseOperations.getDriver().switchTo().alert();

        assertEquals("I am alerting you!",
                alert.getText(),
                String.format("Incorrect alert! Im expecting 'I am alerting you' but got %s", alert.getText()));

        alert.accept();
    }
}
