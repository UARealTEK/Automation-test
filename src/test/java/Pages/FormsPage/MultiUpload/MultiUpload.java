package Pages.FormsPage.MultiUpload;

import Enums.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static Enums.Files.*;

public class MultiUpload {

    private static final Logger log = LogManager.getLogger(MultiUpload.class);
    private final WebDriver driver;

    public MultiUpload(WebDriver driver) {
        this.driver = driver;
    }

    //Upload Certificates (Multiple Files)
    private final By UploadCertificate = By.id("upload_files");

    //Upload Certificates (Multiple Files) Label
    private final By uploadCertificateState = By.id("validate_files");
    private final By uploadCertificateLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='upload_files']");


    public WebElement getMultiFileUploadElement() {
        return driver.findElement(UploadCertificate);
    }

    public WebElement getMultiFileUploadState() {
        return driver.findElement(uploadCertificateState);
    }

    public String getMultiFileUploadStateLabel() {
        return getMultiFileUploadState().getText();
    }

    public WebElement getMultiFileUploadHeader() {
        return driver.findElement(uploadCertificateLabel);
    }

    public String getMultiFileUploadElementID() {
        return getMultiFileUploadElement().getAttribute("id");
    }

    public String getMultiFileUploadHeaderFor(){
        return getMultiFileUploadHeader().getAttribute("for");
    }

    public boolean isElementAndLabelMatched() {
        Optional<String> elementId = Optional.ofNullable(getMultiFileUploadElementID());
        Optional<String> headerFor = Optional.ofNullable(getMultiFileUploadHeaderFor());

        return elementId.equals(headerFor);
    }

    private void fileUploadMac(Files... filenames) {
        String fullPath = getMultiFilePathsMac(filenames);
        WebElement input = getMultiFileUploadElement();
        log.debug(fullPath);

        input.sendKeys(fullPath);
    }

    private void fileUploadWindows(Files... filenames) {
        String fullPath = getMultiFilePathsWindows(filenames);
        WebElement input = getMultiFileUploadElement();
        log.debug(fullPath);

        input.sendKeys(fullPath);
    }

    public void fileUploadMulti(Files... filenames) {
        int macFilesCount = 0;
        int windowsFilesCount = 0;

        for (int i = 0; i < getMacFiles().size(); i++) {
            int finalI = i;
            if (Arrays.stream(filenames).anyMatch(file -> file.name().equalsIgnoreCase(Files.getMacFiles().get(finalI).name()))) {
                macFilesCount++;
            }
        }

        for (int i = 0; i < getWindowsFiles().size(); i++) {
            int finalI = i;
            if (Arrays.stream(filenames).anyMatch(file -> file.name().equalsIgnoreCase(Files.getWindowsFiles().get(finalI).name()))) {
                windowsFilesCount++;
            }
        }

        log.debug("Total amount of files is: {}", Stream.concat(getMacFiles().stream(),getWindowsFiles().stream()).toList().size());
        log.debug("Amount of Windows files are: {}",Files.getWindowsFiles().size());
        log.debug("Amount of Mac Files are: {}",Files.getMacFiles().size());
        log.debug("we have counted {} mac files that were passed to us in the list",macFilesCount);
        log.debug("we have counted {} windows files that were passed to us in a list", windowsFilesCount);

        if (macFilesCount == 0 && windowsFilesCount == filenames.length) {
            fileUploadWindows(filenames);
        } else if (macFilesCount == filenames.length && windowsFilesCount == 0) {
            fileUploadMac(filenames);
        } else throw new IllegalArgumentException("mismatch of arguments");
    }
}

