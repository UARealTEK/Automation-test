package Pages.FormsPage.FileDownload;

import Utils.BaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FileDownload {

    private static final Logger log = LogManager.getLogger(FileDownload.class);
    private final WebDriver driver;
    private static final String downloadDirectoryWindowsPath = "C:/Users/Vova/Downloads/";

    //Download file
    private final By downloadFile = By.id("download_file");

    //Download file Label
    private final By downloadFileLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='download_file']");

    public FileDownload(WebDriver driver) {
        this.driver = driver;
    }

    public static String getWindowsDirectoryPath() {
        return downloadDirectoryWindowsPath;
    }

    public WebElement getDownloadFileButton() {
        return driver.findElement(downloadFile);
    }

    public WebElement getDownloadFileLabel() {
        return driver.findElement(downloadFileLabel);
    }

    public boolean isLabelAndButtonMatched() {
        Optional<String> buttonID = Optional.ofNullable(getDownloadFileButton().getAttribute("id"));
        Optional<String> labelFor = Optional.ofNullable(getDownloadFileLabel().getAttribute("for"));

        if (buttonID.isPresent() && labelFor.isPresent()) {
            return buttonID.get().equalsIgnoreCase(labelFor.get());
        } else throw new IllegalArgumentException("Both or either argument is missing");
    }

    public boolean isCursorStatePointer() {
        return BaseOperations.getJavaScriptComputedPropertyValue(getDownloadFileButton(),"cursor")
                .equalsIgnoreCase("pointer");
    }

    public Boolean isDirectoryUpdated() {
        File directory = new File(getWindowsDirectoryPath());

        if (!directory.exists() || !directory.isDirectory()) {
            return false;
        }
        Boolean isUpdated;
        String[] filesBefore = Optional.ofNullable(directory.list()).orElse(new String[0]);
        isUpdated = BaseOperations.getWait().until((ExpectedCondition<Boolean>) wd -> {
            String[] filesAfter = Optional.ofNullable(directory.list()).orElse(new String[0]);
            log.debug("amount of files after downloading: {}", filesAfter.length);

            return filesAfter.length > filesBefore.length;
        });

        log.debug("amount of files before downloading: {}", filesBefore.length);

        return isUpdated;
    }
}
