package Pages.FormsPage.ValidationForm;

import Enums.FormField;
import Utils.BaseOperations;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    private final By inputFields = By.xpath("//form[@class='needs-validation']//input");

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
    @Getter
    private final String expectedTextSuccessColor = "rgba(63, 182, 24, 1)";
    @Getter // why 0.83? if its 1px
    private final String expectedDefaultBorderColor = "0.833333px solid rgb(206, 212, 218)";
    @Getter
    private final String expectedSuccessBorderValidateColor = "rgb(63, 182, 24)";
    @Getter
    private final String expectedValidateBackgroundImage = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='%23FF0039' viewBox='-2 -2 7 7'%3e%3cpath stroke='%23FF0039' d='M0 0l3 3m0-3L0 3'/%3e%3ccircle r='.5'/%3e%3ccircle cx='3' r='.5'/%3e%3ccircle cy='3' r='.5'/%3e%3ccircle cx='3' cy='3' r='.5'/%3e%3c/svg%3E\")";
    @Getter
    private final String expectedSuccessBackgroundImage = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 8'%3e%3cpath fill='%233FB618' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e\")";

    public List<WebElement> getAllInputs() {
        return driver.findElements(inputFields);
    }

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
        return getDisplayCssPropertyValue(getCityValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedValidateBackgroundImage());
    }

    public boolean isStateFieldValidationTriggered() {
        return getDisplayCssPropertyValue(getStateValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedValidateBackgroundImage());
    }

    public boolean isZipFieldValidationTriggered() {
        return getDisplayCssPropertyValue(getZipValidate()).equalsIgnoreCase("block")
                && getBorderColorCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedValidateBackgroundImage());
    }

    public boolean isTermsFieldValidationTriggered() {
        return getDisplayCssPropertyValue(getTermsValidate()).equalsIgnoreCase("block")
                && getTermsLabel().getCssValue("color").equalsIgnoreCase(getExpectedTextValidateColor());
    }

    public boolean isAllFieldsValidationTriggered() {
        return isCityFieldValidationTriggered()
                && isStateFieldValidationTriggered()
                && isZipFieldValidationTriggered()
                && isTermsFieldValidationTriggered();
    }

    public boolean isCityFieldSuccess() {
        return getDisplayCssPropertyValue(getCityValidate()).equalsIgnoreCase("none")
                && getBorderColorCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedSuccessBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getCityField()).equalsIgnoreCase(getExpectedSuccessBackgroundImage());
    }

    public boolean isStateFieldSuccess() {
        return getDisplayCssPropertyValue(getStateValidate()).equalsIgnoreCase("none")
                && getBorderColorCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedSuccessBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getStateField()).equalsIgnoreCase(getExpectedSuccessBackgroundImage());
    }

    public boolean isZipFieldSuccess() {
        return getDisplayCssPropertyValue(getZipValidate()).equalsIgnoreCase("none")
                && getBorderColorCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedSuccessBorderValidateColor())
                && getBackgroundImageCssPropertyValue(getZipField()).equalsIgnoreCase(getExpectedSuccessBackgroundImage());
    }

    public boolean isTermsFieldSuccess() {
        return getDisplayCssPropertyValue(getTermsValidate()).equalsIgnoreCase("none")
                && getTermsLabel().getCssValue("color").equalsIgnoreCase(getExpectedTextSuccessColor());
    }

    public boolean isAllFieldsSuccess() {
        return isCityFieldSuccess()
                && isStateFieldSuccess()
                && isZipFieldSuccess()
                && isTermsFieldSuccess();
    }

    private void setDataForCityField(WebDriver driver) {
        Actions action = new Actions(driver);

        action.click(getCityField())
                .sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()))
                .build()
                .perform();

    }

    private void setDataForStateField(WebDriver driver) {
        Actions action = new Actions(driver);

        action.click(getStateField())
                .sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()))
                .build()
                .perform();

    }

    private void setDataForZipField(WebDriver driver) {
        Actions action = new Actions(driver);

        action.click(getZipField())
                .sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()))
                .build()
                .perform();

    }

    public void setDataForField(WebDriver driver, FormField field) {
        switch (field) {
            case CITY -> setDataForCityField(driver);
            case STATE -> setDataForStateField(driver);
            case ZIP -> setDataForZipField(driver);
            default -> throw new IllegalArgumentException();
        }
    }

    public void setDataForAllFields(WebDriver driver) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<FormField> list = new ArrayList<>(FormField.getFormFieldsList());

        while (!list.isEmpty()) {
            int index = random.nextInt(list.size());
            setDataForField(driver,list.get(index));
            list.remove(index);
        }
    }

    public void setDataForRandomField(WebDriver driver) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        setDataForField(driver,FormField.getFormFieldsList()
                .get(random.nextInt(FormField.getFormFieldsList().size())));
    }

    public void waitForValidatedForm() throws InterruptedException {
        List<WebElement> elements = getAllInputs();
        BaseOperations.getWait().until(driver1 -> {
            Boolean isAllInputSuccess = elements.stream()
                    .allMatch(element -> getBorderColorCssPropertyValue(element).equalsIgnoreCase(getExpectedSuccessBorderValidateColor()));
            for (WebElement element : elements) {
                getBorderColorCssPropertyValue(element);
            }
            Boolean isLabelSuccess = getTermsLabel().getCssValue("color").equalsIgnoreCase(getExpectedTextSuccessColor());
            log.info(getTermsLabel().getCssValue("color"));

            return isAllInputSuccess && isLabelSuccess;
        });

        Thread.sleep(1000);
    }
}
