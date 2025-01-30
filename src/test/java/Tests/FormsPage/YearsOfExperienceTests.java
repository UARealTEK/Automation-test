package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.YearsOfExperience;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class YearsOfExperienceTests extends DriverOperations {

    @Test
    public void checkYearsOfExperienceDefaultBehavior() {
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SoftAssertions soft = new SoftAssertions();
        YearsOfExperience page = new YearsOfExperience(getDriver());

        //Check placeholder of the field
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.getExpectedYearsOfExperiencePlaceholder());

        page.getYearsOfExperienceField().click();

        //Check that placeholder is NOT gone
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.getExpectedYearsOfExperiencePlaceholder());

        page.insertDataIntoYearsOfExperienceField();

        //Check the placeholder is gone by checking that the value in the field does not contain the placeholder value
        soft.assertThat(page.getYearsOfExperienceInsertedValue().contains(page.getYearsOfExperiencePlaceholder())).isFalse();

        //Check the label data for the YearsOfExperience field
        soft.assertThat(page.getYearsOfExperienceFieldLabel()).isEqualTo(page.getYearsOfExperienceInsertedValue());

        soft.assertAll();
    }

}
