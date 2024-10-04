package SamplePages.Tests;

import Pages.LoginPage;
import Pages.OrderPage;
import Utils.BaseOperations;
import SamplePages.Helper.EndToEndFlows;
import Utils.TestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SamplePageFunctionalTests extends BaseOperations {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static final String firstName = "Vova";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";
    private static final String loginPageURL = "https://play1.automationcamp.ir/login.html";
    private static final String orderPageURL = "https://play1.automationcamp.ir/order_submit.html";
    private WebDriver driver = TestUtils.getDriver();

    //TBD - signup and other stuff


    @Test
    public void login() {
        LoginPage login = new LoginPage(driver);
        driver.get(loginPageURL);

        assertEquals(driver
                .getCurrentUrl(), orderPageURL, "Login failed. Expected" + orderPageURL + " Current Url is " + driver.getCurrentUrl());
    }

    @Test
    public void SignUp() {
        EndToEndFlows.singUp(firstName,lastName,email,password);

        assertEquals(BaseOperations
                .getDriver()
                .getCurrentUrl(), "https://play1.automationcamp.ir/confirmation.html", "SignUp failed. Expected - https://play1.automationcamp.ir/confirmation.html. Current URL is " + BaseOperations.getDriver().getCurrentUrl());
    }


    //TBD
    @Test
    public void setOrder() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage loginPage = new LoginPage(driver);
        OrderPage orderPage = loginPage.validUserLogIn(userName, password);
        orderPage.placeOrder(orderPage);

        // Check that message is appeared after sometime
        WebDriverWait waitMessageToAppear = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitMessageToAppear.until(ExpectedConditions.visibilityOfElementLocated())

        //Then check that the message is GONE after some time
        BaseOperations.getWait().until(ExpectedConditions.invisibilityOf(successConfirmationMessage));
        soft.assertThat(successConfirmationMessage.isDisplayed())
                .as("Nope, Message is still there for some reason")
                .isFalse();
    }
}
