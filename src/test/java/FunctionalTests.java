import helperClasses.BaseOperations;
import helperClasses.EndToEndFlows;
import helperClasses.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class FunctionalTests extends BaseOperations {
    private static final String login = "admin";
    private static final String password = "admin";
    private static final String firstName = "Vova";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";


    @Test
    public void login() {
        EndToEndFlows.login(login,password);

        Assert.assertEquals(BaseOperations
                .getDriver()
                .getCurrentUrl(), "https://play1.automationcamp.ir/order_submit.html", "Login failed. Expected - https://play1.automationcamp.ir/order_submit.html. Current Url is " + BaseOperations.getDriver().getCurrentUrl());
    }

    @Test
    public void SignUp() {
        EndToEndFlows.singUp(firstName,lastName,email,password);

        Assert.assertEquals(BaseOperations
                .getDriver()
                .getCurrentUrl(), "https://play1.automationcamp.ir/confirmation.html", "SignUp failed. Expected - https://play1.automationcamp.ir/confirmation.html. Current URL is " + BaseOperations.getDriver().getCurrentUrl());
    }

    @Test
    public void setOrder() {
        EndToEndFlows.placeOrder(login,password);

        //First check that the message is displayed after some time
        WebElement successConfirmationMessage = BaseOperations.locateElementBy("//*[@id=\"added_message\"]","xpath");
        BaseOperations.getWait().until(ExpectedConditions.visibilityOf(successConfirmationMessage));
        Assert.assertTrue(successConfirmationMessage.isDisplayed(),"Nope, it is NOT yet displayed after button click");

        //Then check that the message is GONE after some time
        BaseOperations.getWait().until(ExpectedConditions.invisibilityOf(successConfirmationMessage));
        Assert.assertFalse(successConfirmationMessage.isDisplayed(), "Nope, Message is still there for some reason");
    }

    @AfterClass
    public void tearDown() {
        TestUtils.quitWebDriver();
    }
}
