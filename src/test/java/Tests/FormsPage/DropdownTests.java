package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class DropdownTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(DropdownTests.class);

    @Test
    public void checkDefaultDropdownState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);

        Select dropdown = FormsPage.getDropdown();
        List<WebElement> dropdownList = FormsPage.getDropdownOptionsList();

        soft.assertThat(!dropdown.getAllSelectedOptions().isEmpty()).isTrue();
        soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                        .isEqualTo(dropdownList.getFirst().getAttribute("value"));

        //Checking that the text in the dropdown mirrors the text for the option which is selected by default
        soft.assertThat(FormsPage.getDropdownText(dropdown)).isEqualTo(dropdown.getFirstSelectedOption().getText());
        soft.assertThat(dropdown.isMultiple()).isFalse();

        //Checking options visibility using JavaScript Triggered Check
        for (WebElement element : dropdownList) {
            soft.assertThat(BaseOperations.isElementVisible(element)).isFalse();
        }

        dropdown.getWrappedElement().click();
        for (WebElement element : dropdownList) {
            soft.assertThat(element.isDisplayed()).isTrue();
        }

        soft.assertThat(!dropdown.getAllSelectedOptions().isEmpty()).isTrue();
        soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                .isEqualTo(dropdownList.getFirst().getAttribute("value"));

        soft.assertAll();
    }
}
