package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class YearsOfExperienceTests extends DriverOperations {

    @Test
    public void checkYearsOfExperienceDefaultBehavior() {
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SoftAssertions soft = new SoftAssertions();
        FormsPage page = new FormsPage(getDriver());

        //Check placeholder of the field
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.expectedYearsOfExperiencePlaceholder);

        BaseOperations.clickElement(BaseOperations.getElement(page.getYearsOfExperienceField()));

        //Check that placeholder is NOT gone
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isEqualTo(page.expectedYearsOfExperiencePlaceholder);

        page.insertDataIntoYearsOfExperienceField(page.getYearsOfExperienceField());

        //Check the placeholder is gone
        soft.assertThat(page.getYearsOfExperiencePlaceholder()).isBlank();

        //Check the label data for the YearsOfExperience field
        soft.assertThat(page.getYearsOfExperienceFieldLabel()).isEqualTo(page.getYearsOfExperienceInsertedData());

        soft.assertAll();
    }

}
