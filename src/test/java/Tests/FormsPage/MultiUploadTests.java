package Tests.FormsPage;

import Enums.Files;
import Enums.URLs;
import Pages.FormsPage.MultiUpload.MultiUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class MultiUploadTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(MultiUploadTests.class);

    /**
     * TODO:
     * - create method for verifying the match between label and uploaded files
     * - create a method that randomly selects files for uploading and apply it in the test
     */
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

    /**
     * TODO:
     * - create method for verifying the match between label and uploaded files
     * - create a method that randomly selects files for uploading and apply it in the test
     */
    @Test
    public void checkMultiFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiUpload page = new MultiUpload(getDriver());

        page.fileUploadMulti(Files.WINDOWS_FILE_1,Files.WINDOWS_FILE_2);

        soft.assertThat(page.isElementAndLabelMatched()).isTrue();
        soft.assertThat(page.getMultiFileUploadStateLabel().isEmpty()).isFalse();
        log.debug(page.getMultiFileUploadStateLabel());

        soft.assertAll();
    }
}
