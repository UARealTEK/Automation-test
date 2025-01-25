package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.MultiUpload.MultiUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class MultiUploadTests extends DriverOperations {

    @Test
    public void checkMultiFileUploadDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiUpload page = new MultiUpload(getDriver());

        soft.assertThat(page.getMultiFileUploadElement().isDisplayed()).isTrue();
        soft.assertThat(page.isElementAndLabelMatched()).isTrue();
        soft.assertThat(page.getMultiFileUploadStateLabel()).isEmpty();

        soft.assertAll();
    }

    @Test
    public void checkMultiFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiUpload page = new MultiUpload(getDriver());




        soft.assertAll();
    }
}
