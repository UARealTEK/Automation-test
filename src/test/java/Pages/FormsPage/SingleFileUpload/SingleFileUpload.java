package Pages.FormsPage.SingleFileUpload;

import Enums.Files;
import Utils.Constants;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;


public class SingleFileUpload {

    private static final Logger log = LogManager.getLogger(SingleFileUpload.class);
    private final WebDriver driver;

    public SingleFileUpload(WebDriver driver) {
        this.driver = driver;
    }

    //Upload CV (Single File)
    private final By uploadCVButtonSingle = By.id("upload_cv");

    //Upload CV (Single File) Label
    private final By uploadCVSingleState = By.id("validate_cv");
    private final By uploadCVSingleLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='upload_cv']");

    public WebElement getUploadCVElement() {
        return driver.findElement(uploadCVButtonSingle);
    }

    private WebElement getUploadCVState() {
        return driver.findElement(uploadCVSingleState);
    }

    public String getUploadCVStateValue () {
        return getUploadCVState().getText();
    }

    public WebElement getUploadCVLabel() {
        return driver.findElement(uploadCVSingleLabel);
    }

    public String getUploadCVIDAttribute() {
        return getUploadCVElement().getAttribute("id");
    }

    public String getUploadCVLabelFORAttribute() {
        return getUploadCVLabel().getAttribute("for");
    }

    public void fileUpload(Files filename) {
        String fullPath = getFilePath(filename);
        WebElement input = getUploadCVElement();
        log.debug(fullPath);

        input.sendKeys(fullPath);
    }

    public String getFilePath(Files filename) {
        String fileName = Files.getFileName(filename.name());
        return String.format(Constants.BASE_FILEPATH_DOWNLOADS_MAC + fileName);
    }
}
