package Tests.FormsPage;

import Enums.Files;
import Enums.URLs;
import Pages.FormsPage.BasicFormControls.MultiUpload.MultiUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MultiUploadTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(MultiUploadTests.class);

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

    @RepeatedTest(3)
    public void checkMultiFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiUpload page = new MultiUpload(getDriver());
        List<Files> filesToUpload = page.generateRandomWindowsFiles();

        page.fileUploadMulti(filesToUpload);

        soft.assertThat(page.isElementAndLabelMatched()).isTrue();
        soft.assertThat(page.getMultiFileUploadStateLabel().isEmpty()).isFalse();
        log.debug(page.getMultiFileUploadStateLabel());
        soft.assertThat(page.isMultiUploadedFileMatchesLabel(filesToUpload)).isTrue();
        log.debug("List of uploaded files: {}", filesToUpload.stream()
                .map(Files::getFileName)
                .collect(Collectors.joining(" ")));

        soft.assertAll();
    }
}
