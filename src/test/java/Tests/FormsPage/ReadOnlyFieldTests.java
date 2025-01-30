package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.ReadOnlyField;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;

@Execution(ExecutionMode.CONCURRENT)
public class ReadOnlyFieldTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(ReadOnlyFieldTests.class);

    @Test
    public void checkReadOnlyFieldDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ReadOnlyField page = new ReadOnlyField(getDriver());

        WebElement textbox = page.getReadOnlyTextbox();
        soft.assertThat(textbox.isDisplayed()).isTrue();
        soft.assertThat(textbox.getAttribute("readonly")).isNotEmpty();
        soft.assertThat(page.getReadOnlyTextboxPlaceholder()).isEqualTo(page.getExpectedReadOnlyPlaceholder());
        soft.assertThat(BaseOperations.isFieldEmpty(textbox)).isTrue();

        BaseOperations.insertRandomTextIntoField(textbox);

        soft.assertThat(BaseOperations.isFieldEmpty(textbox)).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkReadOnlyFieldClearing() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        ReadOnlyField page = new ReadOnlyField(getDriver());
        WebElement textbox = page.getReadOnlyTextbox();

        BaseOperations.insertRandomTextIntoField(textbox);
        soft.assertThat(BaseOperations.isFieldEmpty(textbox)).isTrue();
        soft.assertThat(BaseOperations.getPseudoElementPropertyValue(textbox,"placeholder","visibility"))
                .isEqualTo("visible");

        try {
            textbox.clear();
            soft.fail("The textbox should not be cleared since it is a read-only field");
        } catch (InvalidElementStateException e) {
            log.debug("The exception says: {}", e.getMessage());
            soft.assertThat(true).isTrue();
        }

        soft.assertThat(BaseOperations.getPseudoElementPropertyValue(textbox,"placeholder","visibility"))
                .isEqualTo("visible");

        soft.assertAll();
    }


}
