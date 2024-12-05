package Tests.ExpectedConditionsTests;

import Enums.URLs;
import Pages.ExpectedConditions.ExpectedConditionsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Execution(ExecutionMode.CONCURRENT) // enabled parallel execution
public class ExpectedConditionsTests extends DriverOperations {
    @Test
    public void checkAlertAppearance() {
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        ExpectedConditionsPage alertPage = new ExpectedConditionsPage(getDriver());
        SoftAssertions soft = new SoftAssertions();

        alertPage.showAlert();
        WebDriverWait smallWait = alertPage.getSmallWait();
        WebDriverWait longWait = alertPage.getLongWait();

            //Check that alert WAS NOT displayed before the MIN time
            try {
                smallWait.until(ExpectedConditions.alertIsPresent());
                soft.assertThat(alertPage.isAlertDisplayed())
                        .as(String.format("Alert WAS displayed before the %s has passed", alertPage.getMinFieldValue()))
                        .isFalse();
            } catch (TimeoutException e) {
                //
            }

            longWait.until(ExpectedConditions.alertIsPresent());
            soft.assertThat(alertPage.isAlertDisplayed())
                    .as(String.format("Alert WAS NOT displayed in the time period between %s and %s seconds", alertPage.getMinFieldValue(), alertPage.getMaxFieldValue()))
                    .isTrue();

        soft.assertAll();
    }

    @Test
    public void checkPromptAppearance() {
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        SoftAssertions soft = new SoftAssertions();

        page.showPrompt();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        try {
            smallWait.until(ExpectedConditions.alertIsPresent());
            soft.assertThat(page.isAlertDisplayed())
                    .as(String.format("Alert WAS displayed before the %s has passed", page.getMinFieldValue()))
                    .isFalse();
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.alertIsPresent());
        soft.assertThat(page.isAlertDisplayed())
                .as(String.format("The alert was NOT displayed in the timeframe between %s and %s seconds", page.getMinFieldValue(),page.getMaxFieldValue()))
                .isTrue();

        soft.assertAll();
    }

    @Test
    public void checkElementAppearance() {
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());

        page.showElement();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        try {
            smallWait.until(ExpectedConditions.visibilityOf(page.getVisibilityTargetElement()));
            soft.assertThat(page.getVisibilityTargetElement().isDisplayed())
                    .as(String.format("Element WAS visible before the %s time has passed", page.getMinFieldValue()))
                    .isFalse();
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.visibilityOf(page.getVisibilityTargetElement()));
        soft.assertThat(page.getVisibilityTargetElement().isDisplayed())
                .as(String.format("The Element was NOT present in the timeframe between %s and %s seconds", page.getMinFieldValue(), page.getMaxFieldValue()))
                .isTrue();

        soft.assertAll();
    }

    @Test
    public void checkElementDisappearance() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());

        page.hideElement();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();


        try {
            smallWait.until(ExpectedConditions.invisibilityOf(page.getInVisibilityTargetElement()));
            soft.assertThat(page.getInVisibilityTargetElement().isDisplayed())
                    .as(String.format("The element WAS not hidden before the %s time is passed", page.getMinFieldValue()))
                    .isTrue();
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.invisibilityOf(page.getInVisibilityTargetElement()));
        soft.assertThat(page.getVisibilityTargetElement().isDisplayed())
                .as(String.format("The element WAS displayed in the timeframe between the %s and %s seconds", page.getMinFieldValue(), page.getMaxFieldValue()))
                .isFalse();

        soft.assertAll();
    }

    @Test
    public void checkEnablingElement() {
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);

        page.enableElement();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        try {
            smallWait.until(ExpectedConditions.elementToBeClickable(page.getEnabledElement()));
            soft.assertThat(page.getEnabledElement().isEnabled())
                    .as(String.format("The element was clickable before the %s seconds has passed", page.getMinFieldValue()))
                    .isFalse();
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.elementToBeClickable(page.getEnabledElement()));
        soft.assertThat(page.getEnabledElement().isEnabled())
                .as(String.format("The element was NOT clickable in the timeframe between %s and %s seconds", page.getMinFieldValue(), page.getMaxFieldValue()))
                .isTrue();

        soft.assertThat(page.getEnabledElement().getAttribute(page.getChangedAttributeToFind())).isEqualTo(page.getChangedAttributeValue())
                .as(String.format("The element %s has the attribute value of %s instead of %s", page.getEnabledElement().getText(), page.getEnabledElement().getAttribute(page.getChangedAttributeToFind()), page.getChangedAttributeValue()));

        soft.assertAll();
    }

    @Test
    public void checkTitleChange() {
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);

        page.triggerTitleChange();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        try {
            smallWait.until(ExpectedConditions.titleIs(page.getTargetTitleName()));
            soft.assertThat(page.getTitle()).isNotEqualTo(page.getTargetTitleName())
                    .as(String.format("The title was changed before %s seconds passed", page.getMinFieldValue()));
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.titleIs(page.getTargetTitleName()));
        soft.assertThat(page.getTitle()).isEqualTo(page.getTargetTitleName())
                .as(String.format("The title was NOT changed in the timeframe between %s and %s seconds", page.getMinFieldValue(), page.getMaxFieldValue()));

        soft.assertAll();
    }

    //What do I do with TWO waits? can I check two conditions at once?
    @Test
    public void checkFieldAndButtonValues() {
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);

        page.triggerTextFieldSpecificValue();
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        try {
            smallWait.until(ExpectedConditions.textToBePresentInElementValue(page.getSpecificField(), page.getFieldSpecificValue()));
            soft.assertThat(page.getSpecificField().getText()).isNotEqualTo(page.getFieldSpecificValue());

            smallWait.until(ExpectedConditions.textToBePresentInElementValue(page.getSpecificButton(), page.getButtonSpecificValue()));
            soft.assertThat(page.getSpecificButton().getText()).isNotEqualTo(page.getButtonSpecificValue());
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.textToBePresentInElementValue(page.getSpecificField(), page.getFieldSpecificValue()));
        soft.assertThat(page.getSpecificField().getText()).isEqualTo(page.getFieldSpecificValue());

        longWait.until(ExpectedConditions.textToBePresentInElementValue(page.getSpecificButton(), page.getButtonSpecificValue()));
        soft.assertThat(page.getSpecificButton().getText()).isEqualTo(page.getButtonSpecificValue());

        soft.assertAll();
    }
}
