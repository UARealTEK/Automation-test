package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

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
        soft.assertThat(BaseOperations.getPseudoElementPropertyValue(page.getToggleSwitchLabelElement(),"before","content")).isNotEmpty();
        soft.assertThat(BaseOperations.getPseudoElementPropertyValue(page.getToggleSwitchLabelElement(),"after","content")).isEmpty();
        log.debug("The 'before' pseudo element display property value is: {}", BaseOperations.getPseudoElementPropertyValue(page.getToggleSwitchLabelElement(),"before","content"));
        log.debug("The 'after' pseudo element display property value is: {}", BaseOperations.getPseudoElementPropertyValue(page.getToggleSwitchLabelElement(),"after","content"));

        soft.assertAll();
    }
}
