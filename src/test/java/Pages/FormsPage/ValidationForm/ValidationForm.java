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
    @Getter
    private final String expectedStatePlaceholder = "State";
    @Getter
    private final String expectedZipPlaceholder = "Zip";
    @Getter
    private final String expectedBorderValidateColor = "rgb(255, 0, 57)";
    @Getter
    private final String expectedTextValidateColor = "rgba(255, 0, 57, 1)";
    @Getter // why 0.83? if its 1px
    private final String expectedDefaultBorderColor = "0.833333px solid rgb(206, 212, 218)";
    @Getter
    private final String expectedValidateBackgroundImage = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='%23FF0039' viewBox='-2 -2 7 7'%3e%3cpath stroke='%23FF0039' d='M0 0l3 3m0-3L0 3'/%3e%3ccircle r='.5'/%3e%3ccircle cx='3' r='.5'/%3e%3ccircle cy='3' r='.5'/%3e%3ccircle cx='3' cy='3' r='.5'/%3e%3c/svg%3E\")";

    public WebElement getCityField() {
        return driver.findElement(cityField);
    }

    public WebElement getCityValidate() {
        return driver.findElement(cityValidate);
    }

    public WebElement getStateField() {
        return driver.findElement(state);
    }

    public WebElement getStateValidate() {
        return driver.findElement(stateValidate);
    }

    public WebElement getZipField() {
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

    public WebElement getTermsValidate() {
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

        if (!getCityField().getText().isEmpty()) {
            isDefault = false;
        }


        if (!getCityField().getCssValue("border").equalsIgnoreCase(expectedDefaultBorderColor)) {
            isDefault = false;
        }

        return isDefault;
    }

    public boolean isStateFieldDefault() {
        boolean isDefault = true;

        String placeholder = getStateField().getAttribute("placeholder");

        if (!getStateField().getText().isEmpty()) {
            isDefault = false;
        }

        if (!getStateField().getCssValue("border").equalsIgnoreCase(getExpectedDefaultBorderColor())) {
            isDefault = false;
        }

        if (placeholder != null && !placeholder.equalsIgnoreCase(getExpectedStatePlaceholder())) {
            isDefault = false;
        }

        return isDefault;
    }

    public boolean isZipFieldDefault() {
        boolean isDefault = true;

        String placeholder = getZipField().getAttribute("placeholder");

        if (!getZipField().getText().isEmpty()) {
            isDefault = false;
        }

        if (!getZipField().getCssValue("border").equalsIgnoreCase(getExpectedDefaultBorderColor())) {
            isDefault = false;
        }

        if (placeholder != null && !placeholder.equalsIgnoreCase(getExpectedZipPlaceholder())) {
            isDefault = false;
        }

        return isDefault;
    }

    public boolean isCheckBoxSelected() {
        return getTermsCheckbox().isSelected();
    }

    private String getDisplayCssPropertyValue(WebElement element) {
        return element.getCssValue("display");
    }

    private String getBorderColorCssPropertyValue(WebElement element) {
        return element.getCssValue("border-color");
    }

    public String getBackgroundImageCssPropertyValue(WebElement element) {
        return element.getCssValue("background-image");
    }

    public boolean isCityFieldValidationTriggered() {
        if (getDisplayCssPropertyValue(getCityValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedValidateBackgroundImage())) {
            return true;
        }
        return false;
    }

    public boolean isStateFieldValidationTriggered() {
        if (getDisplayCssPropertyValue(getStateValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedValidateBackgroundImage())) {
            return true;
        }
        return false;
    }

    public boolean isZipFieldValidationTriggered() {
        if (getDisplayCssPropertyValue(getZipValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedValidateBackgroundImage())) {
            return true;
        }
        return false;
    }

    public boolean isTermsFieldValidationTriggered() {
        if (getDisplayCssPropertyValue(getTermsValidate()).equalsIgnoreCase("block")
                && getTermsLabel().getCssValue("color").equalsIgnoreCase(getExpectedTextValidateColor())) {
            return true;
        }
        return false;
    }

    public boolean isAllFieldsValidationTriggered() {
        if (isCityFieldValidationTriggered()
                && isStateFieldValidationTriggered()
                && isZipFieldValidationTriggered()
                && isTermsFieldValidationTriggered()) {
            return true;
        }
        return false;
    }
}
