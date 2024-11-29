package Tests.SamplePages;

import Enums.URLs;
import Pages.SamplePage.LoginPage;
import Pages.SamplePage.OrderPage;
import Pages.SamplePage.RegisterPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT) // enabled parallel execution
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SamplePageTests extends BaseOperations {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static final String firstName = "Volodymyr";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";
    private static final String successMessage = "Pizza added to the cart!";
    private static final String errorMessage = "Quantity must be 1 or more!";
    private static final String loaderText = "Adding to the cart...";
//    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDriver localDriver = null;

    @BeforeEach
    public synchronized void setup() {
//        driver.set(DriverOperations.getDriver());
        localDriver = DriverOperations.getDriver();
    }

    @AfterEach
    public synchronized void tearDown() {
        localDriver.close();
    }

    @AfterAll
    public static void closeWebDriver() {
        DriverOperations.quitWebDriver();
    }

    @Test
    public void login() {
        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage login = new LoginPage(localDriver);
        login.validUserLogIn(userName,password);

        assertEquals(localDriver
                .getCurrentUrl(), BaseOperations.getFullURL(URLs.ORDER_PAGE), "Login failed. Expected" + BaseOperations.getFullURL(URLs.ORDER_PAGE) + " Current Url is " + localDriver.getCurrentUrl());
    }

    @Test
    public void SignUp() {
        SoftAssertions soft = new SoftAssertions();

        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(localDriver);

        RegisterPage registerPage = loginPage.goToSignUp();
        registerPage.validUserSignUp(firstName,lastName,email,password);

        soft.assertThat(localDriver.getCurrentUrl())
                .isEqualTo(BaseOperations.getFullURL(URLs.SIGN_UP_CONFIRMATION))
                .as("User was redirected to the wrong page. Expected - " + BaseOperations.getFullURL(URLs.SIGN_UP_CONFIRMATION) + "but got " + localDriver.getCurrentUrl());

        soft.assertAll();
    }

    @Test
    public void setOrder() {
        SoftAssertions soft = new SoftAssertions();

        BaseOperations.navigateTo(URLs.LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(localDriver);
        OrderPage orderPage = loginPage.validUserLogIn(userName, password);
        orderPage.placeOrder(orderPage);

        //Check loader
        soft.assertThat(orderPage.isLoaderDisplayed())
                .as("Loader was NOT displayed")
                .isTrue();

        //Check Loader Message
        soft.assertThat(orderPage.getLoaderMessage())
                .isEqualTo(loaderText);

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
        LoginPage loginPage = new LoginPage(localDriver);

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
