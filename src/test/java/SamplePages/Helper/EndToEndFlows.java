package SamplePages.Helper;

import Utils.BaseOperations;
import org.openqa.selenium.WebElement;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndToEndFlows extends BaseOperations {

    public static void singUp(String firstName, String lastName, String email, String password) {
        openPage("https://play1.automationcamp.ir/login.html");

        //find register Button and click on it
        WebElement registerButton = locateElementBy("/html/body/div/form/div[4]/a", "xpath");

        clickElement(registerButton);

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
