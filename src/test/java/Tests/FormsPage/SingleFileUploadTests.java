package Tests.FormsPage;

import Enums.Files;
import Enums.URLs;
import Pages.FormsPage.BasicFormControls.SingleFileUpload.SingleFileUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class SingleFileUploadTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(SingleFileUploadTests.class);

    @Test
    public void checkFileUploadDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SingleFileUpload page = new SingleFileUpload(getDriver());

        soft.assertThat(page.getUploadCVElement().isDisplayed()).isTrue();
        soft.assertThat(page.isInputAndLabelMatched()).isTrue();
        soft.assertThat(page.getUploadCVStateValue()).isEmpty();
        soft.assertThat(page.isUploadedFileMatchesLabel(null)).isTrue();
        log.debug("Label value for case when no file is uploaded: {}", page.getUploadCVStateValue());

        soft.assertAll();
    }

    @Test
    public void checkFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SingleFileUpload page = new SingleFileUpload(getDriver());
        Files fileToUpload = page.generateRandomWindowsSingleFile();

        page.fileUploadSingle(fileToUpload);
        log.debug("filename is: {}", fileToUpload.getFileName());
        log.debug("randomly generated file name is: {}", fileToUpload);
        log.info(page.getUploadCVStateValue());
        soft.assertThat(page.getUploadCVStateValue().isEmpty()).isFalse();
        soft.assertThat(page.isInputAndLabelMatched()).isTrue();
        soft.assertThat(page.isUploadedFileMatchesLabel(fileToUpload)).isTrue();
        soft.assertAll();
    }
}
