package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private By firstNameFiled = By.name("first_name");
    private By lastNameField = By.name("last_name");
    private By emailField = By.name("email");
    private By passwordField = By.id("pwd1");
    private By confirmPasswordField = By.id("pwd2");
    private By termsCheckBox = By.name("terms");
    private By signUpButton = By.id("submit_button");


    RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage validUserSignUp(String firstName, String lastName, String email, String password) {
        driver.findElement(firstNameFiled).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(termsCheckBox).click();
        driver.findElement(signUpButton).click();
        return new OrderPage(driver);
    }


}
