package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class ToggleSwitchTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(ToggleSwitchTests.class);

    @Test
    public void checkDefaultToggleSwitchState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        soft.assertThat(!page.isToggleSwitchChecked()).isTrue();
        log.debug(page.getToggleSwitch().getAttribute("checked"));
        soft.assertThat(!page.getToggleSwitch().isDisplayed()).isTrue();
        soft.assertThat(!page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isTrue();
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }

    //GetAttribute() method returns "true" after the toggle switch due to its implementation
    @Test
    public void checkToggleSwitchSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());
        log.debug(page.isToggleSwitchChecked());
        log.debug(page.getToggleSwitch().getAttribute("checked"));


        page.getToggleSwitchLabelElement().click();

        soft.assertThat(page.isToggleSwitchChecked()).isTrue();
        log.debug(page.isToggleSwitchChecked());
        log.debug(page.getToggleSwitch().getAttribute("checked"));
        soft.assertThat(!page.getToggleSwitch().isDisplayed()).isTrue();
        soft.assertThat(page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isFalse();
        log.debug(page.getToggleSwitchValidateLabel());
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkToggleStateAfterPageReload() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        page.getToggleSwitchLabelElement().click();

        soft.assertThat(page.isToggleSwitchChecked()).isTrue();
        soft.assertThat(page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isFalse();
        soft.assertThat(page.isLabelMatched()).isTrue();

        BaseOperations.reloadPage(getDriver());

        soft.assertThat(page.isToggleSwitchChecked()).isFalse();
        soft.assertThat(page.getToggleSwitch().isSelected()).isFalse();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isTrue();
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkToggleSwitchUsingSpaceKeyButton() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());
        Actions action = new Actions(getDriver());

        soft.assertThat(BaseOperations.isFocused(page.getToggleSwitch())).isFalse();

        BaseOperations.focusOnElement(page.getToggleSwitch());
        soft.assertThat(BaseOperations.isFocused(page.getToggleSwitch())).isTrue();
        log.debug(BaseOperations.isFocused(page.getToggleSwitch()));

        action.sendKeys(Keys.SPACE).perform();

        soft.assertThat(page.isToggleSwitchChecked()).isTrue();
        soft.assertThat(page.getToggleSwitch().isSelected()).isTrue();
        soft.assertThat(page.getToggleSwitchValidateLabel().isEmpty()).isFalse();
        soft.assertThat(page.isLabelMatched()).isTrue();

        soft.assertAll();
    }
}
