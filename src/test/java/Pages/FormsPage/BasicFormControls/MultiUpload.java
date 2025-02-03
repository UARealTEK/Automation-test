package Pages.FormsPage.BasicFormControls;

import Enums.Files;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

    public void fileMultiUpload(Files... filenames) {
        String fullPath = getMultiFilePaths(filenames);
        WebElement input = getMultiFileUploadElement();

        input.sendKeys(fullPath);
    }

    public void fileMultiUpload(List<Files> filenames) {
//        log.debug(DriverOperations.getDownloadDirectory());
        String fullPath = getMultiFilePaths(filenames);
//        log.debug(fullPath);
        WebElement input = getMultiFileUploadElement();

        input.sendKeys(fullPath);
    }

    public List<Files> generateRandomFiles() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int amountToGenerate = random.nextInt(getFiles().size());
        List<Files> files = new ArrayList<>(getFiles());
        HashSet<Files> output = new HashSet<>();

        while (amountToGenerate != 0) {
            int randomIndex = random.nextInt(files.size());
            Files fileToAdd = files.get(randomIndex);
            output.add(fileToAdd);
            amountToGenerate--;
            files.remove(randomIndex);
        }

        return new ArrayList<>(output);
    }

    public boolean isMultiUploadedFileMatchesLabel(List<Files> uploadedFiles) {
        if (uploadedFiles == null) {
            return getMultiFileUploadStateLabel().isEmpty();
        }

        StringBuilder out = new StringBuilder();
        for (Files file : uploadedFiles) {
            out.append(file.getFileName()).append(" ");
        }

        return getMultiFileUploadStateLabel().equalsIgnoreCase(out.toString().trim());
    }
}

