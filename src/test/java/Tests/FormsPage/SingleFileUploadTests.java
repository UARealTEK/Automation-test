package Tests.FormsPage;

import Enums.Files;
import Enums.URLs;
import Pages.FormsPage.SingleFileUpload.SingleFileUpload;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

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

        soft.assertAll();
    }

    @Test
    public void checkFileUploadProcess() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        SingleFileUpload page = new SingleFileUpload(getDriver());

        page.fileUpload(Files.FILE_3);
        log.debug(page.getUploadCVStateValue());
        soft.assertThat(page.getUploadCVStateValue().isEmpty()).isFalse();
        soft.assertAll();
    }
}
