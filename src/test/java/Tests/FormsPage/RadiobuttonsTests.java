package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class RadiobuttonsTests extends DriverOperations {

    @Test
    public void verifyLabelForAttributeMatchesInputId() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> radiobuttons = page.getListOfRadioButtons();
        List<WebElement> radiobuttonLabels = page.getListOfRadioButtonLabels();
        List<String> radiobuttonLabelAttributes = new ArrayList<>();

        for (WebElement label : radiobuttonLabels) {
            radiobuttonLabelAttributes.add(label.getAttribute("for"));
        }

        for (WebElement radiobutton : radiobuttons) {
            soft.assertThat(radiobuttonLabelAttributes.contains(radiobutton.getAttribute("id"))).isTrue();
        }

        soft.assertAll();
    }

    @Test
    public void checkRadioButtonDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> radiobuttons = page.getListOfRadioButtons();

        for (WebElement radiobutton : radiobuttons) {
            soft.assertThat(radiobutton.isDisplayed() && radiobutton.isEnabled()).isTrue();
        }

        soft.assertAll();
    }

    @Test
    public void checkRadiobuttonsSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> radiobuttons = page.getListOfRadioButtons();

        for (WebElement radiobutton : radiobuttons) {
            radiobutton.click();
            List<WebElement> unselectedRadioButtons = new ArrayList<>();
            for (WebElement unselectedRadiobutton : radiobuttons) {
                if (!unselectedRadiobutton.isSelected()) {
                    unselectedRadioButtons.add(unselectedRadiobutton);
                }
            }

            soft.assertThat(radiobuttons.size() == unselectedRadioButtons.size() + 1).isTrue();
        }

        soft.assertAll();
    }

}
