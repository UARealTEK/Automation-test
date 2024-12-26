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

        List<WebElement> radiobuttons = FormsPage.getListOfRadioButtons();
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

        List<WebElement> radiobuttons = FormsPage.getListOfRadioButtons();

        for (WebElement radiobutton : radiobuttons) {
            soft.assertThat(radiobutton.isDisplayed() && radiobutton.isEnabled() && (!radiobutton.isSelected())).isTrue();
        }

        soft.assertAll();
    }

    @Test
    public void checkRadiobuttonsSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        List<WebElement> radiobuttons = FormsPage.getListOfRadioButtons();

        for (WebElement radiobutton : radiobuttons) {
            radiobutton.click();
            String checkedState = radiobutton.getAttribute("checked");
            soft.assertThat(checkedState).isEqualTo("true");
            List<WebElement> unselectedRadioButtons = new ArrayList<>();
            for (WebElement unselectedRadiobutton : radiobuttons) {
                if (!unselectedRadiobutton.isSelected()) {
                    soft.assertThat(unselectedRadiobutton.getAttribute("checked")).isEqualTo(null);
                    unselectedRadioButtons.add(unselectedRadiobutton);
                }
            }

            soft.assertThat(radiobuttons.size() == unselectedRadioButtons.size() + 1).isTrue();
        }

        soft.assertAll();
    }

    @Test
    public void verifyButtonStateAfterReload() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> radiobuttons = FormsPage.getListOfRadioButtons();

        page.selectRandomRadiobutton();

        int countOfUnselectedButtons = 0;
        for (WebElement radiobutton : radiobuttons) {
            if (!radiobutton.isSelected()) {
                countOfUnselectedButtons++;
            }
        }

        soft.assertThat(countOfUnselectedButtons == radiobuttons.size() - 1).isTrue();

        getDriver().navigate().refresh();
        radiobuttons = FormsPage.getListOfRadioButtons();

        for (WebElement radiobutton : radiobuttons) {
            soft.assertThat(radiobutton.isSelected()).isFalse();
        }

        soft.assertAll();
    }

    @Test
    public void checkSelectedRadiobuttonLabel() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        page.selectRandomRadiobutton();
        List<WebElement> radiobuttons = FormsPage.getListOfCheckboxes();
        String label = page.getSelectedRadiobuttonsLabel();

        for (WebElement radiobutton : radiobuttons) {
            if (radiobutton.isSelected()) {
                soft.assertThat(radiobutton.getAttribute("value")).isEqualTo(label);
            }
        }

        soft.assertAll();
    }

    @Test
    public void checkRadiobuttonSelectionViaLabel() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> radiobuttons = FormsPage.getListOfRadioButtons();
        List<WebElement> radiobuttonLabels = FormsPage.getListOfRadioButtonLabels();

        //Checking that for each RadiobuttonLabel there's a corres
        for (WebElement label : radiobuttonLabels) {
            String currentLabelForAttribute = label.getAttribute("for");
            boolean isCorrespondingButtonPresent = radiobuttons.stream()
                            .anyMatch(r -> r.getAttribute("id")
                            .equals(currentLabelForAttribute));

            //Clicking the label if its corresponding button is FOUND in the radiobuttons list
            if (isCorrespondingButtonPresent){
                label.click();
            }

            List<WebElement> unselectedButtons = new ArrayList<>();
            for (WebElement radiobutton : radiobuttons) {
                if ((!radiobutton.isSelected()) && radiobutton.getAttribute("checked") == null) {
                    unselectedButtons.add(radiobutton);
                }
            }

            //Check that all other buttons are unselected after clicking on the label
            soft.assertThat(unselectedButtons.size() == radiobuttons.size() -1).isTrue();

        }
        
    }

    /**
     * check that after clicking on the label the radiobutton is being selected
     *
     * TODO:
     * Verify that for each specific radiobutton, theres a corresponding label in other list
     * (work with labels)
     * Click that label
     * Check that all other radiobuttons in the list are unchecked
     */

}
