package Utils; // File: src/test/java/your/package/SamplePagesTests.helperClasses.TestUtils.java

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {

    private static WebDriver driver;

    // Method to initialize WebDriver
    public static WebDriver initializeWebDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/Users/volodymyrprydatko/Downloads/chromedriver-mac-arm64/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().fullscreen();
        }

        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeWebDriver();
        }
        return driver;
    }

    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
