package Tests.SamplePages;

import Enums.URLs;
import Pages.SamplePage.LoginPage;
import Pages.SamplePage.OrderPage;
import Pages.SamplePage.RegisterPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SamplePageTests extends BaseOperations {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static final String firstName = "Volodymyr";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";
    private static final String successMessage = "Pizza added to the cart!";
    private static final String errorMessage = "Quantity must be 1 or more!";
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
    public void login() {
        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage login = new LoginPage(driver);
        login.validUserLogIn(userName,password);

        assertEquals(driver
                .getCurrentUrl(), BaseOperations.getFullURL(URLs.ORDER_PAGE), "Login failed. Expected" + BaseOperations.getFullURL(URLs.ORDER_PAGE) + " Current Url is " + driver.getCurrentUrl());
    }

    @Test
    public void SignUp() {
        SoftAssertions soft = new SoftAssertions();

        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(driver);

        RegisterPage registerPage = loginPage.goToSignUp();
        registerPage.validUserSignUp(firstName,lastName,email,password);

        soft.assertThat(driver.getCurrentUrl())
                .isEqualTo(BaseOperations.getFullURL(URLs.SIGN_UP_CONFIRMATION))
                .as("User was redirected to the wrong page. Expected - " + BaseOperations.getFullURL(URLs.SIGN_UP_CONFIRMATION) + "but got " + driver.getCurrentUrl());

        soft.assertAll();
    }

    @Test
    public void setOrder() {
        SoftAssertions soft = new SoftAssertions();

        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(driver);
        OrderPage orderPage = loginPage.validUserLogIn(userName, password);
        orderPage.placeOrder(orderPage);

        // Check that message is appeared after sometime
        soft.assertThat(orderPage.isSuccessMessageDisplayed())
                .as("the Message was not displayed")
                .isTrue();

        // Check MessageText
        soft.assertThat(orderPage.getSuccessMessage())
                .isEqualTo(successMessage)
                .as(String.format("Message was incorrect. Expected %s but got %s", successMessage, orderPage.getSuccessMessage()));

        //Then check that the message is GONE after some time
        soft.assertThat(orderPage.isSuccessMessageDisappeared())
                .as("the Message was still displayed")
                .isTrue();

        soft.assertAll();
    }

    @Test
    public void checkErrorMessage() throws InterruptedException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(driver);

        OrderPage orderPage = loginPage.validUserLogIn(userName, password);
        orderPage.placeInvalidOrder(orderPage);

        //Check that error Message was displayed after a short period of time
        soft.assertThat(orderPage.isErrorMessageDisplayed())
                .as("The error message was NOT displayed")
                .isTrue();

        //Check Error Message Text
        soft.assertThat(orderPage.getErrorMessage())
                .isEqualTo(errorMessage);

        //Check that error Modal can be closed
        orderPage.closeErrorModal();
        soft.assertThat(orderPage.isErrorMessageDisplayed())
                .as("The error message was NOT closed")
                .isFalse();

        soft.assertAll();
    }
}
