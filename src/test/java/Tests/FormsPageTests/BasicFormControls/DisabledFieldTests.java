package Tests.FormsPageTests.BasicFormControls;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.DisabledField;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Tag("BasicFormControls")
@Execution(ExecutionMode.CONCURRENT)
public class DisabledFieldTests extends DriverOperations {

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
