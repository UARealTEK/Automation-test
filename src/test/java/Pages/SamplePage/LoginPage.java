package Pages.SamplePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//

public class LoginPage {
    private WebDriver driver;
    private By userLogin = By.id("user");
    private By userPassword = By.id("password");
    private By loginButton = By.id("login");
    private By registerButton = By.linkText("register.html");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage validUserLogIn(String name, String password) {
        driver.findElement(userLogin).sendKeys(name);
        driver.findElement(userPassword).sendKeys(password);
        driver.findElement(loginButton).click();
        return new OrderPage(driver);
    }

    public RegisterPage goToSignUp() {
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }

}
