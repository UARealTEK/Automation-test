package Utils; // File: src/test/java/your/package/SamplePagesTests.helperClasses.TestUtils.java
// Windows - "C:/Users/Volodymyr/Documents/Visual Studio 2022/chromedriver"
// Mac - "/Users/volodymyrprydatko/Downloads/chromedriver-mac-arm64/chromedriver"
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class DriverOperations {

    private static final ThreadLocal<WebDriver> driverThread = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver(getBrowserOptions());
        driver.manage().window().maximize();
        return driver;
    });

    protected static ChromeOptions getBrowserOptions() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/Utils/Driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Для запуска без UI
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

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove(); // Удаляем экземпляр из ThreadLocal
        }
    }
}
