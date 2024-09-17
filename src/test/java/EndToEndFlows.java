import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EndToEndFlows extends BaseOperations{
    public static void login(String email, String password) {
        //open the page
        openPage("https://play1.automationcamp.ir/login.html");

        //locate the username field, insert data in it and click it
        WebElement username = locateElementBy("//*[@id=\"user\"]", "xpath");
        clickElement(username);
        username.sendKeys(email);

        WebElement passwordField = locateElementBy("//*[@id=\"password\"]", "xpath");
        clickElement(passwordField);
        passwordField.sendKeys(password);

        WebElement loginButton = locateElementBy("//*[@id=\"login\"]", "xpath");
        clickElement(loginButton);
    }

    public static void singUp(String firstName, String lastName, String email, String password) {
        openPage("https://play1.automationcamp.ir/login.html");

        //find register Button and click on it
        WebElement registerButton = locateElementBy("/html/body/div/form/div[4]/a", "xpath");

        // Check SignUp button text
        Assert.assertEquals(registerButton.getText(), "New user? Register!", "Nope, should be: 'New User? Register!' but got: " + registerButton.getText());

        clickElement(registerButton);

        //Check that user got redirected to SignUp page
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://play1.automationcamp.ir/register.html", "Should be 'https://play1.automationcamp.ir/register.html', but got: " + getDriver().getCurrentUrl());

        // Insert user data
        WebElement firstNameField = locateElementBy("/html/body/div/form/div[1]/div/div[1]/input", "xpath");
        clickElement(firstNameField);
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = locateElementBy("/html/body/div/form/div[1]/div/div[2]/input", "xpath");
        clickElement(lastNameField);
        lastNameField.sendKeys(lastName);

        WebElement emailField = locateElementBy("/html/body/div/form/div[2]/input","xpath");
        clickElement(emailField);
        emailField.sendKeys(email);

        WebElement passwordField = locateElementBy("//*[@id=\"pwd1\"]","xpath");
        clickElement(passwordField);
        passwordField.sendKeys(password);

        WebElement confirmPasswordField = locateElementBy("//*[@id=\"pwd2\"]","xpath");
        clickElement(confirmPasswordField);
        confirmPasswordField.sendKeys(password);

        //Tick the checkbox to Agree to Terms of Use
        WebElement agreeToTerms = locateElementBy("/html/body/div/form/div[5]/label/input", "xpath");
        clickElement(agreeToTerms);

        //Click on the signUp button
        WebElement signUpButton = locateElementBy("//*[@id=\"submit_button\"]", "xpath");
        clickElement(signUpButton);
    }
}
