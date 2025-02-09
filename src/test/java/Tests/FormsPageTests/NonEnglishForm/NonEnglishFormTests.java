package Tests.FormsPageTests.NonEnglishForm;

import Enums.URLs;
import Pages.FormsPage.NonEnglishForm.NonEnglishForm;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

public class NonEnglishFormTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(NonEnglishFormTests.class);

    @Test
    public void checkFormDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        NonEnglishForm page = new NonEnglishForm(getDriver());

        soft.assertThat(page.isFieldDefault()).isTrue();
        soft.assertThat(page.isCheckboxesDefault()).isTrue();
        soft.assertThat(page.isFieldAndLabelMatched()).isTrue();
        soft.assertThat(page.isFieldAndValidateMatched()).isTrue();
        soft.assertThat(page.isCheckboxesAndLabelMatched()).isTrue();
        soft.assertThat(page.isCheckboxesAndValidateMatched()).isTrue();

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkDataInsertionIntoField() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        NonEnglishForm page = new NonEnglishForm(getDriver());

        page.insertDataIntoField(getDriver());

        soft.assertThat(page.isFieldDefault()).isFalse();
        soft.assertThat(page.isFieldAndValidateMatched()).isTrue();

        log.info(page.getInputField().getAttribute("value"));
        log.info(page.getInputFieldValidate().getText());

        soft.assertAll();
    }

    @RepeatedTest(5)
    public void checkCheckboxesSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        NonEnglishForm page = new NonEnglishForm(getDriver());

        page.selectRandomCheckboxes();

        soft.assertThat(page.isCheckboxesAndValidateMatched()).isTrue();
        log.info(page.getCheckboxValidate().getText());
        log.info(page.getCheckboxes().stream()
                .filter(WebElement::isSelected)
                .map(checkbox -> checkbox.getAttribute("value"))
                .collect(Collectors.joining(" ")));

        soft.assertAll();
    }
}
