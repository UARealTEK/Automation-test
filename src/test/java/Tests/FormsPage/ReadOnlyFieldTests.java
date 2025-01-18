package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;

public class ReadOnlyFieldTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(ReadOnlyFieldTests.class);

    @Test
    public void checkReadOnlyFieldDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        WebElement textbox = FormsPage.getReadOnlyTextbox();
        soft.assertThat(textbox.isDisplayed()).isTrue();
        soft.assertThat(textbox.getAttribute("readonly")).isNotEmpty();
        soft.assertThat(FormsPage.getReadOnlyTextboxPlaceholder()).isEqualTo(FormsPage.getExpectedReadOnlyPlaceholder());
        soft.assertThat(BaseOperations.isFieldEmpty(textbox)).isTrue();

        FormsPage.insertRandomTextIntoField(textbox);

        soft.assertThat(BaseOperations.isFieldEmpty(textbox)).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkReadOnlyFieldClearing() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        WebElement textbox = FormsPage.getReadOnlyTextbox();

        FormsPage.insertRandomTextIntoField(textbox);
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
