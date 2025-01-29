package Pages.FormsPage.BasicFormControls.ValidationForm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationForm {

    private final WebDriver driver;


    public ValidationForm(WebDriver driver) {
        this.driver = driver;
    }

    private By getCityField = By.id("validationCustom03");
    private By getState = By.id("validationCustom04");
    private By getZip = By.id("validationCustom05");
    private By getTermsLabel = By.xpath("//div[@class='form-check']/label[@for='invalidCheck']");
    private By getTermsCheckbox = By.id("invalidCheck");
    private By getTermsValidation = By.id("invalid_terms");
    private By getSubmitButton = By.xpath("//button[@class='btn btn-primary' and @type='submit']");


}
