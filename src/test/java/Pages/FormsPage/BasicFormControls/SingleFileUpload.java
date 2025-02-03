package Pages.FormsPage.BasicFormControls;

import Enums.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static Enums.Files.*;


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

    public void fileUploadSingle(Files file) {
        String fullPath = getFilePath(file);
        WebElement input = getUploadCVElement();
        log.debug(fullPath);

        input.sendKeys(fullPath);
    }

    public boolean isInputAndLabelMatched() {
        Optional<String> inputID = Optional.ofNullable(getUploadCVIDAttribute());
        Optional<String> labelFor = Optional.ofNullable(getUploadCVLabelFORAttribute());
        return inputID.equals(labelFor);
    }

    public Files generateRandomSingleFile() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Files> files = new ArrayList<>(getFiles());

        int randomIndex = random.nextInt(files.size());
        return files.get(randomIndex);
    }

    public boolean isUploadedFileMatchesLabel(Files uploadedFile) {

        if (uploadedFile == null) {
            return getUploadCVStateValue().isEmpty();
        }

        return getUploadCVStateValue().equalsIgnoreCase(uploadedFile.getFileName());
    }
}
