package Tests.FormsPageTests.BasicFormControls;

import Enums.Files;
import Enums.URLs;
import Pages.FormsPage.BasicFormControls.MultiUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

@Tag("BasicFormControls")
@Execution(ExecutionMode.CONCURRENT)
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

    @RepeatedTest(3)
    public void checkMultiFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        MultiUpload page = new MultiUpload(getDriver());
        List<Files> filesToUpload = page.generateRandomFiles();

        page.fileMultiUpload(filesToUpload);

        soft.assertThat(page.isElementAndLabelMatched()).isTrue();
        soft.assertThat(page.getMultiFileUploadStateLabel().isEmpty()).isFalse();
//        log.debug(page.getMultiFileUploadStateLabel());
        soft.assertThat(page.isMultiUploadedFileMatchesLabel(filesToUpload)).isTrue();
//        log.debug("List of uploaded files: {}", filesToUpload.stream()
//                .map(Files::getFileName)
//                .collect(Collectors.joining(" ")));

        soft.assertAll();
    }
}
