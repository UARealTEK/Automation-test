package SamplePages.Helper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void setup() {
        TestUtils.initializeWebDriver(); // Initialize the WebDriver before each test
    }

    @AfterEach
    public void tearDown() {
        TestUtils.quitWebDriver(); // Clean up the WebDriver after each test
    }
}
