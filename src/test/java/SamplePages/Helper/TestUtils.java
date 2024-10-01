package SamplePages.Helper; // File: src/test/java/your/package/SamplePagesTests.helperClasses.TestUtils.java

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {

    private static WebDriver driver;
    private static WebDriverWait wait;

    // Method to initialize WebDriver
    public static void initializeWebDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/Users/volodymyrprydatko/Downloads/chromedriver-mac-arm64/chromedriver");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            options.addArguments("--headless");
            driver.manage().window().fullscreen();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        TestUtils.wait = wait;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeWebDriver();
        }
        return driver;
    }

    @BeforeEach
    public void setup() {
        TestUtils.initializeWebDriver(); // Initialize the WebDriver before each test
    }

    @AfterEach
    public void tearDown() {
        TestUtils.quitWebDriver(); // Clean up the WebDriver after each test
    }

    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
