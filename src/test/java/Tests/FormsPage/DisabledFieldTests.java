package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.DisabledField.DisabledField;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class DisabledFieldTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(DisabledFieldTests.class);

    @Test
    public void checkDisabledFieldDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        DisabledField page = new DisabledField(getDriver());

        soft.assertThat(page.isTextBoxAndLabelMatched()).isTrue();
        soft.assertThat(page.isFieldDisabled()).isTrue();
        soft.assertThat(page.isPlaceholderMatched()).isTrue();

        getDriver().navigate().refresh();
        soft.assertThat(page.isFieldDisabled()).isTrue();

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkDisabledFieldIsDisabled() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        DisabledField page = new DisabledField(getDriver());

        soft.assertThat(page.isFieldDisabled()).isTrue();
        soft.assertThat(page.isDisabledTextBoxFocused()).isFalse();
        soft.assertThat(page.isBackgroundColorMatched()).isTrue();

        page.sendKeysToDisabledTextBox();

        soft.assertThat(page.isDisabledTextBoxEmpty()).isTrue();

        soft.assertAll();
    }
}
