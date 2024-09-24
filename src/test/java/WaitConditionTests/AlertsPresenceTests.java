package WaitConditionTests;

import SamplePagesTests.helperClasses.BaseOperations;
import SamplePagesTests.helperClasses.TestUtils;
import WaitConditionTests.helperClasses.AlertsBaseOperations;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class AlertsPresenceTests extends BaseOperations {

    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isDisplayedAlertAfterDelay(2,5)),
                DynamicTest.dynamicTest("mi - 7, max - 10", () -> isDisplayedAlertAfterDelay(7,10)));
    }


    public void isDisplayedAlertAfterDelay(int min, int max) {
        AlertsBaseOperations.showAlert(2, 5); // Assuming these are seconds

        WebDriverWait shortWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(min));
        try {
            shortWait.until(ExpectedConditions.alertIsPresent());
            System.out.println(String.format("Алерт з'явився раніше, ніж через %s секунди.", min));
        } catch (Exception e) {
            System.out.println(String.format("Алерт не з'явився протягом перших %s секунд, продовжуємо чекати...",min));
        }
        // Максимальна затримка для чекання
        WebDriverWait longWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(max - min));

        try {
        Alert alert = longWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(String.format("Алерт з'явився в інтервалі від %s до %s секунд.",min,max));
        alert.accept(); // Закриває алерт
            }
        catch (Exception e) {
        System.out.println(String.format("Алерт не з'явився навіть через %s секунд.",max));            }
    }

    @AfterAll
    public static void tearDown() {
        TestUtils.quitWebDriver();
    }

}
