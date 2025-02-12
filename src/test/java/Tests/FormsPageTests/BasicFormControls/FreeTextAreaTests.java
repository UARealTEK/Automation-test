package Tests.FormsPageTests.BasicFormControls;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.FreeTextArea;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
@Tag("BasicFormControls")
@Execution(ExecutionMode.CONCURRENT)
public class FreeTextAreaTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(FreeTextAreaTests.class);

    @Test
    public void checkTextAreaDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FreeTextArea page = new FreeTextArea(getDriver());

        soft.assertThat(page.getTextAreaField().getText()).isEmpty();
        soft.assertThat(page.getTextAreaFieldLabel().isEmpty()).isTrue();
        soft.assertThat(page.getTextAreaFieldPlaceholder())
                .isEqualTo(page.getExpectedFreeTextAreaPlaceholder());

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkTextAreaDataInsertion() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FreeTextArea page = new FreeTextArea(getDriver());

        BaseOperations.insertRandomTextIntoField(page.getTextAreaField());
        log.debug("The inserted text is: {}", page.getTextAreaInsertedText());
        log.debug("The label value is {}", page.getTextAreaFieldLabel());

        soft.assertThat(page.isPlaceholderVisible(page.getTextAreaField())).isFalse();
        soft.assertThat(page.getTextAreaInsertedText())
                .isEqualTo(page.getTextAreaFieldLabel());

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkTextAreaStateAfterPageReload() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FreeTextArea page = new FreeTextArea(getDriver());

        BaseOperations.insertRandomTextIntoField(page.getTextAreaField());
        soft.assertThat(page.isPlaceholderVisible(page.getTextAreaField())).isFalse();
        soft.assertThat(page.getTextAreaInsertedText().isEmpty()).isFalse();
        soft.assertThat(page.getTextAreaFieldLabel().isEmpty()).isFalse();
        log.debug("The currently inserted text in the field is: {}", page.getTextAreaInsertedText());
        log.debug("The currently displayed label is: {}", page.getTextAreaFieldLabel());

        BaseOperations.reloadPage(getDriver());

        soft.assertThat(page.isPlaceholderVisible(page.getTextAreaField())).isTrue();
        soft.assertThat(page.getTextAreaInsertedText().isEmpty()).isTrue();
        soft.assertThat(page.getTextAreaFieldLabel().isEmpty()).isTrue();
        log.debug("The currently inserted text in the field is: {}", page.getTextAreaInsertedText());
        log.debug("The currently displayed label is: {}", page.getTextAreaFieldLabel());

        soft.assertAll();
    }

    @Test
    public void checkTextAreaResizing() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FreeTextArea page = new FreeTextArea(getDriver());
        WebElement textArea = page.getTextAreaField();

        int currentHeight = textArea.getSize().getHeight();
        page.resizeTextAreaViaJS(textArea,getDriver(),200);
        int newHeight = textArea.getSize().getHeight();
        soft.assertThat(currentHeight + 200 == newHeight).isTrue();
        log.debug("current Height is: {}", currentHeight);
        log.debug("new Height is: {}", newHeight);

        soft.assertAll();
    }

    @Test
    public void checkScrollbarAppearance() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FreeTextArea page = new FreeTextArea(getDriver());
        WebElement textArea = page.getTextAreaField();

        do {
            BaseOperations.insertRandomTextIntoField(textArea);
        } while (!page.hasVerticalScrollbar(textArea));

        int scrollHeight = page.getTextAreaScrollHeight(textArea);
        int clientHeight = page.getTextAreaClientHeight(textArea);

        log.debug("The scrollHeight is: {}", scrollHeight);
        log.debug("The clientHeight is: {}", clientHeight);
        soft.assertThat(scrollHeight > clientHeight).isTrue();
        soft.assertAll();
    }

}
