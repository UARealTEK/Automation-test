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

import java.util.List;
import java.util.Optional;

@Execution(ExecutionMode.CONCURRENT)
public class MultiselectDropdownTests extends DriverOperations {

    @Test
    public void checkDropdownDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        List<WebElement> dropdownOptions = FormsPage
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
}
