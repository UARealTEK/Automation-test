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

public class CheckboxesTests  extends DriverOperations {

    @Test
    public void checkCheckboxesDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> checkboxes = page.getListOfCheckboxes();

        //Check that all checkboxes are unchecked by default
        for (int i = 0; i < checkboxes.size(); i++) {
           soft.assertThat(checkboxes.get(i).isSelected()).isFalse();
           System.out.println(checkboxes.get(i).getCssValue("checked"));
//           soft.assertThat(checkboxes.get(i).getAttribute("checked").equals("false")).isTrue();
        }

        //Check that a specific checkbox is currently disabled (Java in this case)
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).getAttribute("id").equals(page.getJavaCheckbox().getAttribute("id"))) {
                soft.assertThat(checkboxes.get(i).isEnabled()).isFalse();
                soft.assertThat(checkboxes.get(i).getCssValue("disabled").equals("true")).isTrue();
            } else {
                soft.assertThat(checkboxes.get(i).isEnabled()).isTrue();
                soft.assertThat(checkboxes.get(i).getCssValue("disabled").equals("false")).isTrue();
            }
        }

        //Place all enabled checkboxes into a list and click each of them to make sure that they can be selected
        List<WebElement> activeCheckboxes = new ArrayList<>();
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isEnabled()) {
                activeCheckboxes.add(checkboxes.get(i));
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
