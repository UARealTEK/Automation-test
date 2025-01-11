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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

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

    @RepeatedTest(3)
    public void checkTextAreaStateAfterPageReload() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        FormsPage.insertRandomTextIntoField(FormsPage.getTextAreaField());
        soft.assertThat(FormsPage.isPlaceholderVisible(FormsPage.getTextAreaField())).isFalse();
        soft.assertThat(FormsPage.getTextAreaInsertedText().isEmpty()).isFalse();
        soft.assertThat(FormsPage.getTextAreaFieldLabel().isEmpty()).isFalse();
        log.debug("The currently inserted text in the field is: {}", FormsPage.getTextAreaInsertedText());
        log.debug("The currently displayed label is: {}", FormsPage.getTextAreaFieldLabel());

        BaseOperations.reloadPage(getDriver());

        soft.assertThat(FormsPage.isPlaceholderVisible(FormsPage.getTextAreaField())).isTrue();
        soft.assertThat(FormsPage.getTextAreaInsertedText().isEmpty()).isTrue();
        soft.assertThat(FormsPage.getTextAreaFieldLabel().isEmpty()).isTrue();
        log.debug("The currently inserted text in the field is: {}", FormsPage.getTextAreaInsertedText());
        log.debug("The currently displayed label is: {}", FormsPage.getTextAreaFieldLabel());

        soft.assertAll();
    }

    @Test
    public void checkTextAreaResizing() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        WebElement textArea = FormsPage.getTextAreaField();

        int currentHeight = textArea.getSize().getHeight();
        FormsPage.resizeTextAreaViaJS(textArea,getDriver(),200);
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
        WebElement textArea = FormsPage.getTextAreaField();
        log.debug("aria role is: {}", textArea.getAriaRole());
    }

}
