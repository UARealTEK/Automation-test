package Tests.FormsPage;
import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckboxesTests  extends DriverOperations {

    @Test
    public void checkCheckboxesDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> checkboxes = FormsPage.getListOfCheckboxes();

        //Check that all checkboxes are unchecked by default
        for (WebElement webElement : checkboxes) {
            soft.assertThat(webElement.isDisplayed()).isTrue();
            soft.assertThat(webElement.isSelected()).isFalse();
        }

        //Check that a specific checkbox is currently disabled (Java in this case)
        for (WebElement element : checkboxes) {
            if (Objects.requireNonNull(element.getAttribute("id")).equalsIgnoreCase(page.getJavaCheckbox().getAttribute("id"))) {
                soft.assertThat(element.isEnabled()).isFalse();
                soft.assertThat(Objects.requireNonNull(BaseOperations.getJavaScriptPropertyValue(element, "disabled")).equalsIgnoreCase("true")).isTrue();
            } else {
                soft.assertThat(element.isEnabled()).isTrue();
                soft.assertThat(Objects.requireNonNull(BaseOperations.getJavaScriptPropertyValue(element, "disabled")).equalsIgnoreCase("false")).isTrue();
            }
        }

        //Place all enabled checkboxes into a list and click each of them to make sure that they can be selected
        List<WebElement> activeCheckboxes = new ArrayList<>();
        for (WebElement element : checkboxes) {
            if (element.isEnabled()) {
                activeCheckboxes.add(element);
            }
        }

        //Check that ENABLED (active) checkboxes can be selected and unselected
        for (WebElement activeCheckbox : activeCheckboxes) {
            activeCheckbox.click();
            soft.assertThat(activeCheckbox.isSelected()).isTrue();
            activeCheckbox.click();
            soft.assertThat(activeCheckbox.isSelected()).isFalse();
        }

        //Check disabled Checkbox cannot be selected
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isEnabled()) {
                checkbox.click();
                soft.assertThat(checkbox.isSelected()).isFalse();
            }
        }

        soft.assertAll();
    }

    @Test
    public void checkCheckboxStateAfterPageReload() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        List<WebElement> checkboxes = FormsPage.getListOfCheckboxes();

        for (WebElement checkbox : checkboxes) {
            if (checkbox.isEnabled()) {
                checkbox.click();
                //Check that each checkbox is selected once clicked. Need to make sure it is before page reload
                soft.assertThat(checkbox.isSelected()).isTrue();
            }
        }

        //page reload
        getDriver().navigate().refresh();

        // Re-fetch the list of checkboxes after page reload - needed because elements become stale
        checkboxes = FormsPage.getListOfCheckboxes();

        //Check that each checkbox is unselected after page reload
        for (WebElement checkbox : checkboxes) {
            soft.assertThat(checkbox.isSelected()).isFalse();
        }

        soft.assertAll();
    }


    //Working. But im not sure how to check that the label for the selected values are displayed IN ORDER
    @Test
    public void checkCheckboxLabelsAfterSelecting() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        //Generate a list of WebElements which will be clicked one by one (same original list but placed on random indices)
        List<WebElement> webElementList = page.selectRandomCheckboxes(); // this is only a button. not a text of that button

        //Click on each checkbox
        for (WebElement checkbox : webElementList) {
            checkbox.click();
        }

        //Verify the correctness of the displayed label
        String selectedCheckboxesLabels = page.getSelectedCheckboxLabels();
        List<String> clickedCheckboxesValues = new ArrayList<>();
        for (int i = 0; i < webElementList.size(); i++) {
            String value = webElementList.get(i).getAttribute("value");
            clickedCheckboxesValues.add(value);
        }

        for (String value : clickedCheckboxesValues) {
            soft.assertThat(selectedCheckboxesLabels.contains(value)).isTrue();
        }

        soft.assertAll();
    }
}
