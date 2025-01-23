package Pages.FormsPage.MultiUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

}
