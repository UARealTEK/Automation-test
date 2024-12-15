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

        List<WebElement> checkboxes = page.getListOfCheckboxes();

        //Check that all checkboxes are unchecked by default
        for (WebElement webElement : checkboxes) {
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
}
