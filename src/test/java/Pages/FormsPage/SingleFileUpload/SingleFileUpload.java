package Pages.FormsPage.SingleFileUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SingleFileUpload {

    private final WebDriver driver;

    public SingleFileUpload(WebDriver driver) {
        this.driver = driver;
    }

    //Upload CV (Single File)
    private final By uploadCVButtonSingle = By.id("upload_cv");

    //Upload CV (Single File) Label
    private final By uploadCVSingleState = By.id("validate_cv");
    private final By uploadCVSingleLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='upload_cv']");

}
