package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class YearsOfExperienceTests extends DriverOperations {

    @Test
    //checks for placeholder value, inserted value, field label
    public void checkYearsOfExperienceDefaultBehavior() {
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SoftAssertions soft = new SoftAssertions();
        FormsPage page = new FormsPage(getDriver());

        //Check placeholder of the field
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.expectedYearsOfExperiencePlaceholder);

        BaseOperations.clickElement(BaseOperations.getElement(page.getYearsOfExperienceField()));
        System.out.println(String.format("Heres the data that is currently in the field: %s", page.getYearsOfExperienceInsertedValue()));

        //Check that placeholder is NOT gone
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.expectedYearsOfExperiencePlaceholder);

        page.insertDataIntoYearsOfExperienceField(page.getYearsOfExperienceField());

        //Check the placeholder is gone by checking that the value in the field does not contain the placeholder value
        soft.assertThat(page.getYearsOfExperienceInsertedValue().contains(page.getYearsOfExperiencePlaceholder())).isFalse();

        //Check the label data for the YearsOfExperience field
        soft.assertThat(page.getYearsOfExperienceFieldLabel()).isEqualTo(page.getYearsOfExperienceInsertedValue());

        soft.assertAll();
    }

}
