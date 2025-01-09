package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Execution(ExecutionMode.CONCURRENT)
public class FreeTextAreaTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(FreeTextAreaTests.class);

    @Test
    public void checkTextAreaDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        soft.assertThat(FormsPage.getTextAreaField().getText()).isEmpty();
        soft.assertThat(FormsPage.getTextAreaFieldLabel().isEmpty()).isTrue();
        soft.assertThat(FormsPage.getTextAreaFieldPlaceholder())
                .isEqualTo(FormsPage.getExpectedFreeTextAreaPlaceholder());

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkTextAreaDataInsertion() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        FormsPage.insertRandomTextIntoField(FormsPage.getTextAreaField());
        log.debug("The inserted text is: {}", FormsPage.getTextAreaInsertedText());
        log.debug("The label value is {}", FormsPage.getTextAreaFieldLabel());

        soft.assertThat(FormsPage.isPlaceholderVisible(FormsPage.getTextAreaField())).isFalse();
        soft.assertThat(FormsPage.getTextAreaInsertedText())
                .isEqualTo(FormsPage.getTextAreaFieldLabel());

        soft.assertAll();
    }

}
