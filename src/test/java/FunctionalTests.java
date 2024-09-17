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
    public void loginTest() {
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

    @AfterClass
    public void tearDown() {
        TestUtils.quitWebDriver();
    }
}
