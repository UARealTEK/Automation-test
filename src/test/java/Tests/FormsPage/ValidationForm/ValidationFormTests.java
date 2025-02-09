package Tests.FormsPage.ValidationForm;

import Enums.URLs;
import Pages.FormsPage.ValidationForm.ValidationForm;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;

public class ValidationFormTests extends DriverOperations {

    @Test
    public void checkFormDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ValidationForm page = new ValidationForm(getDriver());

        soft.assertThat(page.isCityFieldDefault()).isTrue();
        soft.assertThat(page.isStateFieldDefault()).isTrue();
        soft.assertThat(page.isZipFieldDefault()).isTrue();
        soft.assertThat(page.isCheckBoxSelected()).isFalse();
        soft.assertThat(page.isAllFieldsValidationTriggered()).isFalse();

        soft.assertAll();
    }

    @Test
    public void checkEmptyFieldFormSubmission() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ValidationForm page = new ValidationForm(getDriver());

        page.getSubmitButton().click();

        soft.assertThat(page.isCityFieldDefault()).isFalse();
        soft.assertThat(page.isStateFieldDefault()).isFalse();
        soft.assertThat(page.isZipFieldDefault()).isFalse();
        soft.assertThat(page.isCheckBoxSelected()).isFalse();
        soft.assertThat(page.isAllFieldsValidationTriggered()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkSuccessfulFormSubmission() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ValidationForm page = new ValidationForm(getDriver());
        WebElement button = page.getSubmitButton();

        page.setDataForAllFields(getDriver());
        page.getTermsCheckbox().click();
        page.getSubmitButton().click();

        //direct approach with checking the "success" state of the elements hasn't worked due to flawed implementation
        page.waitForStaleForm(button);

        soft.assertThat(page.isAllFieldsValidationTriggered()).isFalse();

        soft.assertAll();
    }

    @Test
    public void checkAllFieldsSuccessState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ValidationForm page = new ValidationForm(getDriver());

        page.setDataForAllFields(getDriver());
        page.getSubmitButton().click();

        soft.assertThat(page.isAllFieldsSuccess()).isTrue();
        soft.assertThat(page.isTermsFieldValidationTriggered()).isTrue();

        page.getTermsCheckbox().click();

        soft.assertThat(page.isTermsFieldSuccess()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkRandomFieldValidation() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ValidationForm page = new ValidationForm(getDriver());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        WebElement field = page.getAllInputs()
                .get(random.nextInt(page.getAllInputs().size()));

        page.setDataForField(getDriver(),field);
        page.getSubmitButton().click();

        soft.assertThat(page.isTermsFieldValidationTriggered()).isTrue();
        soft.assertThat(page.isFieldValidationTriggered(field)).isTrue();

        soft.assertAll();
    }
}
