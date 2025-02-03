package Utils; // File: src/test/java/your/package/SamplePagesTests.helperClasses.TestUtils.java
// Windows - "C:/Users/Volodymyr/Documents/Visual Studio 2022/chromedriver"
import Pages.FormsPage.BasicFormControls.FileDownload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class DriverOperations {

    private static final ThreadLocal<WebDriver> driverThread = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver(getBrowserOptions());
        driver.manage().window().maximize();
        return driver;
    });

    private static final ThreadLocal<WebDriverWait> threadLocalWait = ThreadLocal.withInitial(() -> new WebDriverWait(getDriver(),Duration.ofSeconds(5)));

    protected static ChromeOptions getBrowserOptions() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Для запуска без UI
        options.addArguments("download.default_directory=" + FileDownload.getWindowsDirectoryPath());
        return options;
    }

    @BeforeEach // how ? why in this class?
    void setUp() {
        // Инициализация драйвера происходит через ThreadLocal
        getDriver();
    }

    @AfterEach
    void tearDown() {
        quitDriver();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static WebDriverWait getWait() {
        return threadLocalWait.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove(); // Удаляем экземпляр из ThreadLocal
        }
    }

    public static String getDriverSystemProperty() {
        return System.getProperty("webdriver.chrome.driver");
    }
}
