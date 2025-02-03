package Pages.FormsPage.ValidationForm;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationForm {

    private static final Logger log = LogManager.getLogger(ValidationForm.class);
    private final WebDriver driver;


    public ValidationForm(WebDriver driver) {
        this.driver = driver;
    }

    private final By cityField = By.id("validationCustom03");
    private final By cityValidate = By.id("invalid_city");
    private final By state = By.id("validationCustom04");
    private final By stateValidate = By.id("invalid_state");
    private final By zip = By.id("validationCustom05");
    private final By zipValidate = By.id("invalid_zip");
    private final By termsLabel = By.xpath("//div[@class='form-check']/label[@for='invalidCheck']");
    private final By termsCheckbox = By.id("invalidCheck");
    private final By termsValidation = By.id("invalid_terms");
    private final By submitButton = By.xpath("//button[@class='btn btn-primary' and @type='submit']");

    @Getter
    private final String expectedCityPlaceholder = "City";
    @Getter // why 0.83? if its 1px
    private final String expectedDefaultBorderColor = "0.833333px solid rgb(206, 212, 218)";

    public WebElement getCityField() {
        return driver.findElement(cityField);
    }

    public WebElement getCityValidate() {
        return driver.findElement(cityValidate);
    }

    public WebElement getState() {
        return driver.findElement(state);
    }

    public WebElement getStateValidate() {
        return driver.findElement(stateValidate);
    }

    public WebElement getZip() {
        return driver.findElement(zip);
    }

    public WebElement getZipValidate() {
        return driver.findElement(zipValidate);
    }

    public WebElement getTermsLabel() {
        return driver.findElement(termsLabel);
    }

    public WebElement getTermsCheckbox() {
        return driver.findElement(termsCheckbox);
    }

    public WebElement getTermsValidation() {
        return driver.findElement(termsValidation);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }

    public boolean isCityFieldDefault() {
        boolean isDefault = true;
        String placeholder = getCityField().getAttribute("placeholder");
        if (placeholder != null && !placeholder.equalsIgnoreCase(getExpectedCityPlaceholder())) {
            isDefault = false;
        }

        log.info("The placeholder state is: {}", placeholder != null && !placeholder.equalsIgnoreCase(getExpectedCityPlaceholder()));

        if (!getCityField().getText().isEmpty()) {
            isDefault = false;
        }

        log.info("The Text inside the City filed state is : {}", !getCityField().getText().isEmpty());

        if (!getCityField().getCssValue("border").equalsIgnoreCase(expectedDefaultBorderColor)) {
            isDefault = false;
        }

        log.info("The border state for the City field is: {}", !getCityField().getCssValue("border").equalsIgnoreCase(expectedDefaultBorderColor));

        return isDefault;
    }
}
