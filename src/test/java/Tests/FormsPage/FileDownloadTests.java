package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.BasicFormControls.FileDownload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileDownloadTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(FileDownloadTests.class);

    @Test
    public void checkFileDownloadDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FileDownload page = new FileDownload(getDriver());

        log.debug("current cursor state is: {}", BaseOperations.getJavaScriptComputedPropertyValue(page.getDownloadFileButton(),"cursor"));
        soft.assertThat(page.isCursorStatePointer()).isTrue();
        soft.assertThat(page.isLabelAndButtonMatched()).isTrue();
        log.debug("button id is: {}", page.getDownloadFileButton().getAttribute("id"));
        log.debug("label 'for' attribute is: {}", page.getDownloadFileLabel().getAttribute("for"));
        soft.assertAll();
    }

    @Test
    public void checkFileDownloadProcess() throws IOException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FileDownload page = new FileDownload(getDriver());

        page.getDownloadFileButton().click();
        soft.assertThat(page.isDirectoryUpdated()).isTrue();
        soft.assertThat(page.isFileNameValid()).isTrue();
        soft.assertThat(page.isFileDataMatched()).isTrue();
        soft.assertAll();
    }

    @Test
    public void checkFileData() throws IOException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FileDownload page = new FileDownload(getDriver());

        page.getDownloadFileButton().click();
        soft.assertThat(page.isDirectoryUpdated()).isTrue();
        soft.assertThat(page.isFileNameValid()).isTrue();
        soft.assertThat(page.isFileDataMatched()).isTrue();
        log.info(page.getDefaultFileName().substring(0,page.getDefaultFileName().length() -4));
        log.debug(page.getLastDownloadedFileData());
        soft.assertAll();
    }

}
