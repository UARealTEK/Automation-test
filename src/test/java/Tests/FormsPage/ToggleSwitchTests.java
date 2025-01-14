package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

public class ToggleSwitchTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(ToggleSwitchTests.class);

    //Why content / display properties are not working? How do I check that the ::before is used when the toggle is unchecked?
    @Test
    public void checkDefaultToggleSwitchState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        soft.assertThat(!page.isToggleSwitchChecked()).isTrue();
        soft.assertThat(!page.getToggleSwitch().isDisplayed()).isTrue();
        soft.assertThat(!page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isTrue();
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkToggleSwitchSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        page.getToggleSwitchLabelElement().click();

        soft.assertThat(page.isToggleSwitchChecked()).isTrue();
        soft.assertThat(!page.getToggleSwitch().isDisplayed()).isTrue();
        soft.assertThat(page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isFalse();
        log.debug(js.executeScript("return window.localStorage.getItem('firstPageLoadFlag');"));
        log.debug(page.getToggleSwitchValidateLabel());
        log.debug(BaseOperations.getJavaScriptPropertyValue(page.getToggleSwitch(),"checked"));
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }
}
