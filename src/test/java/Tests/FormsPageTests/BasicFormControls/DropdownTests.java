package Tests.FormsPageTests.BasicFormControls;

import Enums.DropdownSelectCriteria;
import Enums.URLs;
import Pages.FormsPage.BasicFormControls.Dropdowns;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag("BasicFormControls")
@Execution(ExecutionMode.CONCURRENT)
public class DropdownTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(DropdownTests.class);

    @Test
    public void checkDefaultDropdownState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        Dropdowns page = new Dropdowns(getDriver());

        Select dropdown = page.getDropdown();
        List<WebElement> dropdownList = page.getDropdownOptionsList();

        //In our case, the first option in the dropdown is the one that is being selected by default. Hence - this is the check
        soft.assertThat(!dropdown.getAllSelectedOptions().isEmpty()).isTrue();
        soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                        .isEqualTo(dropdownList.getFirst().getAttribute("value"));
        log.debug("The option which was selected by default is: {}", dropdown.getFirstSelectedOption().getAttribute("value"));
        log.debug("The first option in the dropdown list is: {}", dropdownList.getFirst());

        //Checking that the text in the dropdown mirrors the text for the option which is selected by default
        soft.assertThat(page.getDropdownText(dropdown)).isEqualTo(dropdown.getFirstSelectedOption().getText());
        soft.assertThat(dropdown.isMultiple()).isFalse();

        //Checking options visibility using JavaScript Triggered Check
        for (WebElement element : dropdownList) {
            soft.assertThat(BaseOperations.isElementVisible(element)).isFalse();
        }

        dropdown.getWrappedElement().click();
        for (WebElement element : dropdownList) {
            soft.assertThat(element.isDisplayed()).isTrue();
        }

        dropdown.getWrappedElement().click();

        //Checking that the label is populated with the option which was selected by the user
        soft.assertThat(page.getDropdownLabel())
                .isEqualTo(dropdown.getFirstSelectedOption().getAttribute("value"));

        log.debug("The label text is: {}", page.getDropdownLabel());
        log.debug("The selected option value is: {}", dropdown.getFirstSelectedOption().getAttribute("value"));

        soft.assertThat(!dropdown.getAllSelectedOptions().isEmpty()).isTrue();
        soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                .isEqualTo(dropdownList.getFirst().getAttribute("value"));

        soft.assertAll();
    }

    @Test
    public void checkOptionSelectionByText() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        Dropdowns page = new Dropdowns(getDriver());

        Select dropdown = page.getDropdown();
        Set<String> selectedOptions = new HashSet<>();
        selectedOptions.add(dropdown.getFirstSelectedOption().getText());

        while (selectedOptions.size() < dropdown.getOptions().size()) {
            page.selectRandomDropdownOptionBy(dropdown,DropdownSelectCriteria.BY_TEXT);
            selectedOptions.add(dropdown.getFirstSelectedOption().getText());
            soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                    .isEqualTo(page.getDropdownLabel());
        }

        soft.assertAll();
    }

    @Test
    public void checkOptionSelectionByValue() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        Dropdowns page = new Dropdowns(getDriver());

        Select dropdown = page.getDropdown();
        Set<String> selectedOptions = new HashSet<>();
        selectedOptions.add(dropdown.getFirstSelectedOption().getText());

        while (selectedOptions.size() < dropdown.getOptions().size()) {
            page.selectRandomDropdownOptionBy(dropdown,DropdownSelectCriteria.BY_VALUE);
            selectedOptions.add(dropdown.getFirstSelectedOption().getText());
            log.debug(dropdown.getFirstSelectedOption().getAttribute("value"));
            log.debug(page.getDropdownLabel());
            soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                    .isEqualTo(page.getDropdownLabel());
        }

        soft.assertAll();
    }

    @Test
    public void checkOptionSelectionByIndex() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        Dropdowns page = new Dropdowns(getDriver());

        Select dropdown = page.getDropdown();
        Set<String> selectedOptions = new HashSet<>();
        selectedOptions.add(dropdown.getFirstSelectedOption().getText());

        while (selectedOptions.size() < dropdown.getOptions().size()) {
            page.selectRandomDropdownOptionBy(dropdown,DropdownSelectCriteria.BY_INDEX);
            selectedOptions.add(dropdown.getFirstSelectedOption().getText());
            log.debug(dropdown.getFirstSelectedOption().getAttribute("value"));
            log.debug(page.getDropdownLabel());
            soft.assertThat(dropdown.getFirstSelectedOption().getAttribute("value"))
                    .isEqualTo(page.getDropdownLabel());
        }

        soft.assertAll();
    }

    @Test
    public void checkDropdownNavigationUsingKeyboard() throws InterruptedException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        Dropdowns page = new Dropdowns(getDriver());

        Select dropdown = page.getDropdown();
        List<WebElement> dropdownOptions = page.getDropdownOptionsList();
        Actions action = new Actions(getDriver());

        for (WebElement option : dropdownOptions) {
            action.click(dropdown.getWrappedElement()).build().perform();
            soft.assertThat(option.getText())
                    .isEqualTo(page.getDropdownText(dropdown));
            log.debug("Current option: {}", option.getText());
            log.debug("Option in the dropdown is: {}", page.getDropdownText(dropdown));

            action
                    .keyDown(Keys.ARROW_DOWN)
                    .keyUp(Keys.ARROW_DOWN)
                    .keyDown(Keys.ENTER)
                    .keyUp(Keys.ENTER)
                    .build()
                    .perform();

            Thread.sleep(300);
        }

        soft.assertAll();
    }



}
