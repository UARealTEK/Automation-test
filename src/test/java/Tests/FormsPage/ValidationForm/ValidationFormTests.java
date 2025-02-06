package Tests.FormsPage.ValidationForm;

import Enums.URLs;
import Pages.FormsPage.ValidationForm.ValidationForm;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ValidationFormTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(ValidationFormTests.class);

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
}
