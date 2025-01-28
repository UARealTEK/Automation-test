package Pages.FormsPage.BasicFormControls.FileDownload;

import Utils.BaseOperations;
import Utils.DriverOperations;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FileDownload {

    private static final Logger log = LogManager.getLogger(FileDownload.class);
    private final WebDriver driver;
    private static final String downloadDirectoryWindowsPath = "C:/Users/Vova/Downloads/";
    private static final String downloadDirectoryMacPath = "/Users/volodymyrprydatko/Downloads/";

    //Download file
    private final By downloadFile = By.id("download_file");

    //Download file Label
    private final By downloadFileLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='download_file']");
    @Getter
    private final String expectedFileContent = "File downloaded by AutomationCamp";
    @Getter
    private final String defaultFileName = "sample_text.txt";

    public FileDownload(WebDriver driver) {
        this.driver = driver;
    }

    public static String getWindowsDirectoryPath() {
        return downloadDirectoryWindowsPath;
    }

    public static String getMacDirectoryPath() {
        return downloadDirectoryMacPath;
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
        File directory;
        if (isDriverSetForMac()) {
            directory = new File(getMacDirectoryPath());
        } else {
            directory = new File(getWindowsDirectoryPath());
        }

        if (!directory.exists()) {
            return false;
        }

        Boolean isUpdated;
        String[] filesBefore = Optional.ofNullable(directory.list()).orElse(new String[0]);
        isUpdated = BaseOperations.getWait().until((ExpectedCondition<Boolean>) wd -> {
            String[] filesAfter = Optional.ofNullable(directory.list()).orElse(new String[0]);

            return filesAfter.length > filesBefore.length;
        });


        return isUpdated;
    }

    public String getExpectedFileName() {
        File directory;

        if (isDriverSetForMac()) {
            directory = new File(getMacDirectoryPath());
        } else directory = new File(getWindowsDirectoryPath());

        File[] fileList = directory.listFiles();
        int countOfDownloadedFiles = 0;

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile() && file.exists() && file.getName().startsWith(getDefaultFileName().substring(0,getDefaultFileName().length() -4))) {
                    countOfDownloadedFiles++;
                }
            }
        }

        if (countOfDownloadedFiles <= 1) {
            return getDefaultFileName();
        } else return String.format(getDefaultFileName().substring(0,getDefaultFileName().length() -4) + " (%s).txt",countOfDownloadedFiles -1);

    }

    public Boolean isFileNameValid() {
        log.debug(getExpectedFileName());
        log.debug(getLastDownloadedFileName());
        return getExpectedFileName().equalsIgnoreCase(getLastDownloadedFileName());
    }

    public String getLastDownloadedFileName() {
        File directory;

        if (isDriverSetForMac()) {
            directory = new File(getMacDirectoryPath());
        } else directory = new File(getWindowsDirectoryPath());

        File[] fileList = directory.listFiles();

        if (fileList == null || fileList.length == 0) {
            return null;
        }

        File lastModifiedFile = fileList[0];
        for (File file : fileList) {
            if (file.lastModified() > lastModifiedFile.lastModified()) {
                lastModifiedFile = file;
            }
        }


        return lastModifiedFile.getName();
    }

    public String getLastDownloadedFileData() throws IOException {
        File directory;

        if (isDriverSetForMac()) {
            directory = new File(getMacDirectoryPath());
        } else directory = new File(getWindowsDirectoryPath());

        File[] fileList = directory.listFiles();

        if (fileList == null || fileList.length == 0) {
            return null;
        }
        File lastModified = Arrays.stream(fileList)
                .filter(file -> file.isFile() && file.exists())
                .max(Comparator.comparing(File::lastModified)).orElse(null);

        StringBuilder out = new StringBuilder();
        if (lastModified != null) {
            List<String> lines = Files.readAllLines(lastModified.toPath());

            for (String line : lines) {
                out.append(line);
            }
        }
        return out.toString();
    }

    public boolean isFileDataMatched() throws IOException {
        return getLastDownloadedFileData().equalsIgnoreCase(getExpectedFileContent());
    }

    public static boolean isDriverSetForMac() {
        return System.getProperty("webdriver.chrome.driver").equalsIgnoreCase(DriverOperations.getDriverSystemProperty());
    }
}
