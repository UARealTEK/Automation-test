package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ReadOnlyFieldTests extends DriverOperations {

    @Test
    public void checkReadOnlyFieldDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        WebElement textbox = FormsPage.getReadOnlyTextbox();
        soft.assertThat(textbox.getAttribute("readonly")).isNotEmpty();
        soft.assertThat(FormsPage.getReadOnlyTextboxPlaceholder()).isEqualTo(FormsPage.getExpectedFreeTextAreaPlaceholder());
        soft.assertThat(FormsPage.getReadOnlyTextboxInsertedData()).isEmpty();

        FormsPage.insertRandomTextIntoField(textbox);

        soft.assertThat(FormsPage.getReadOnlyTextboxInsertedData()).isEmpty();
    }
}
