package Pages.FormsPage.MultiUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class MultiUpload {

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
}
