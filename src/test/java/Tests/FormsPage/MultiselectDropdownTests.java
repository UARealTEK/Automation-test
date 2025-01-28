package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.MultiselectDropdown.MultiselectDropdown;
import Utils.BaseOperations;
import Utils.DriverOperations;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Execution(ExecutionMode.CONCURRENT)
public class MultiselectDropdownTests extends DriverOperations {

    @Test
    public void checkDropdownDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiselectDropdown page = new MultiselectDropdown(getDriver());

        List<WebElement> dropdownOptions = page
                .getMultiSelectDropdown()
                .getOptions();

        Optional<WebElement> dropdownLabel = Optional.ofNullable(page.getSelectedDropdownOptionsLabel());
        soft.assertThat(dropdownLabel.isPresent()).isTrue(); // Ensure it's present
        dropdownLabel.ifPresent(label -> soft.assertThat(label.getText()).isEmpty());

        soft.assertThat(dropdownOptions).isNotNull();
        soft.assertThat(dropdownOptions.isEmpty()).isFalse();


        for (WebElement option : dropdownOptions) {
            soft.assertThat(option.isSelected()).isFalse();
        }

        soft.assertAll();
    }

    @Test
    public void checkElementMultiSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiselectDropdown page = new MultiselectDropdown(getDriver());

        List<WebElement> dropdownOptions = page
                .getMultiSelectDropdown()
                .getOptions();

        StringBuilder labelBuilder = new StringBuilder();

        int index = 0;
        Actions action = new Actions(getDriver());
        action.keyDown(Keys.CONTROL).perform();
        while (page.getCountOfSelectedOptions() < dropdownOptions.size()) {
            WebElement element = dropdownOptions.get(index);
            action.click(element).perform();
            soft.assertThat(element.isSelected()).isTrue();
            labelBuilder.append(element.getAttribute("value"));
            labelBuilder.append(",");
            index++;
        }
        action.keyUp(Keys.CONTROL).perform();
        String actualLabel = labelBuilder.substring(0,labelBuilder.length() - 1);
        String expectedLabel = page.getSelectedDropdownOptionsLabel().getText();

        boolean isAllSelected = dropdownOptions.stream().allMatch(WebElement::isSelected);

        soft.assertThat(isAllSelected).isTrue();

        //Checking that the label is matched with the selected options
        soft.assertThat(actualLabel).isEqualTo(expectedLabel)
                .as(String.format("The label is not correct. I have received ' %s ', but expecting ' %s ' ", actualLabel,expectedLabel));

        soft.assertAll();
    }

    @Test
    public void checkSelectingRandomOptions() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiselectDropdown page = new MultiselectDropdown(getDriver());

        StringBuilder labelBuilder = new StringBuilder();
        List<WebElement> list = page.getMultiSelectDropdown().getOptions();

        page.selectRandomOptions(list,list.size());
        for (WebElement element : list) {
            if (element.isSelected()) {
                labelBuilder.append(element.getAttribute("value")).append(",");
            }
        }

        String actualLabel = labelBuilder.substring(0,labelBuilder.length() -1);
        String expectedLabel = page.getSelectedDropdownOptionsLabel().getText();

        soft.assertThat(actualLabel).isEqualTo(expectedLabel)
                .as(String.format("The label is not correct. I have received ' %s ', but expecting ' %s ' ", actualLabel,expectedLabel));

        soft.assertAll();
    }

    @Test
    public void checkOptionDeselection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiselectDropdown page = new MultiselectDropdown(getDriver());

        List<WebElement> list = page
                .getMultiSelectDropdown()
                .getOptions();

        page.selectRandomOptions(list,list.size());

        List<WebElement> selectedOptions = page.getSelectedOptionsList();
        for (WebElement option : selectedOptions) {
            int indexOption = selectedOptions.indexOf(option);
            page.deselectedOptionAtIndex(selectedOptions,indexOption);
            soft.assertThat(option.isSelected()).isFalse();
            selectedOptions = page.getSelectedOptionsList();

            String currentActualSelectedOptions = selectedOptions.stream()
                    .map(label -> label.getAttribute("value"))
                    .collect(Collectors.joining(","));

            String currentLabel = page.getSelectedDropdownOptionsLabel().getText();

            log.debug("Current label: '{}' vs actual selected options: '{}'", currentLabel, currentActualSelectedOptions);

            // Assert that the selected options' value matches the dropdown label
            soft.assertThat(currentActualSelectedOptions)
                    .as(String.format("Expected selected options: '%s', but got: '%s'", currentActualSelectedOptions, currentLabel))
                    .isEqualTo(currentLabel);        }
        soft.assertAll();
    }
}
