package Tests.ExpectedConditionsTests;

import Enums.URLs;
import Pages.ExpectedConditions.ExpectedConditionsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//TDB: Implement creating both waits in the DriverOperations class

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

    //Line 194 - how does it know which driver to use?
    @Test
    public void checkFieldAndButtonValues() {
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        //Variable that checks both values of both field and button
        ExpectedCondition<Boolean> bothTextsToBePresent =
                (driver) -> page.getSpecificField().getAttribute("value").equals(page.getTargetFieldValue())
                        && page.getSpecificButton().getText().equals(page.getTargetButtonValue());

        //Check that values were not changed until the smallWait time is passed
        Boolean smallWaitResult = false;
        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        page.triggerTextFieldSpecificValue();

        try {
            smallWaitResult = smallWait.until(bothTextsToBePresent);
        } catch (TimeoutException e) {
            //
        }
        soft.assertThat(smallWaitResult).isFalse();

        //Check that values WERE in fact changed during the timeframe of longWait - smallWait
        Boolean longWaitResult = false;
        try {
            longWaitResult = longWait.until(bothTextsToBePresent);
        } catch (TimeoutException e) {
            System.out.println((String.format("The vales were NOT changed in the timeframe between %s and %s seconds", page.getMinFieldValue(), page.getMaxFieldValue())));

            System.out.println((String.format("The value of the specific field is: %s, but I expect %s", page.getSpecificField().getAttribute("value"), page.getTargetFieldValue())));
            System.out.println((String.format("The value of the specific button is: %s, but I expect %s", page.getSpecificButton().getText(), page.getTargetButtonValue())));
        }
        soft.assertThat(longWaitResult).isTrue();

        soft.assertAll();
    }

    //Not working. TBD
    @Test
    public void checkFrameAppearance() {
        SoftAssertions soft = new SoftAssertions();
        ExpectedConditionsPage page = new ExpectedConditionsPage(getDriver());
        WebDriverWait smallWait = page.getSmallWait();
        WebDriverWait longWait = page.getLongWait();

        BaseOperations.navigateTo(URLs.EXPECTED_CONDITIONS);
        page.triggerFrameAppearance();

        try {
            smallWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(page.getFrameLocator()));
            soft.assertThat(page.getFrameDocumentTitle()).isNotEqualTo(page.getTargetFrameTitleText());
        } catch (TimeoutException e) {
            //
        }

        longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(page.getFrameLocator()));
        soft.assertThat(page.getFrameDocumentTitle()).isEqualTo(page.getTargetFrameTitleText());

        soft.assertAll();
    }
}
