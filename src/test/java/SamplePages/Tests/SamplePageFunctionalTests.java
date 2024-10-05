package SamplePages.Tests;

import Enums.URLs;
import Pages.SamplePage.LoginPage;
import Pages.SamplePage.OrderPage;
import Pages.SamplePage.RegisterPage;
import Utils.BaseOperations;
import Utils.TestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class SamplePageFunctionalTests extends BaseOperations {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static final String firstName = "Vova";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";
    private static final String successMessage = "Pizza added to the cart!";
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = TestUtils.getDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
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
}
