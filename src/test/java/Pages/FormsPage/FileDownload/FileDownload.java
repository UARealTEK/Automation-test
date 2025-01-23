package Pages.FormsPage.FileDownload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileDownload {

    private final WebDriver driver;

    //Download file
    private final By downloadFile = By.id("download_file");

    //Download file Label
    private final By downloadFileLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='download_file']");

    public FileDownload(WebDriver driver) {
        this.driver = driver;
    }
}
