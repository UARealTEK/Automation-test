package Tests.ExpectedConditionsTests;

import Enums.URLs;
import Pages.ExpectedConditions.ExpectedConditionsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpectedConditionsTests extends BaseOperations {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = DriverOperations.getDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        DriverOperations.quitWebDriver();
    }

    @Test
    public void checkAlertAppearance() {
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        ExpectedConditionsPage alertPage = new ExpectedConditionsPage(driver);
        SoftAssertions soft = new SoftAssertions();

        alertPage.showAlert();
        WebDriverWait smallWait = alertPage.getSmallWait();
        WebDriverWait longWait = alertPage.getLongWait();

            //Check that alert WAS NOT displayed before the MIN time
            try {
                smallWait.until(ExpectedConditions.alertIsPresent());
                soft.assertThat(alertPage.isAlertDisplayed())
                        .as(String.format("Alert WAS displayed before the %s has passed", alertPage.getMinFieldValue()))
                        .isFalse();
            } catch (TimeoutException e) {
                //
            }

            longWait.until(ExpectedConditions.alertIsPresent());
            soft.assertThat(alertPage.isAlertDisplayed())
                    .as(String.format("Alert WAS NOT displayed in the time period between %s and %s seconds", alertPage.getMinFieldValue(), alertPage.getMaxFieldValue()))
                    .isTrue();

        soft.assertAll();
    }
}
