package Utils;

import Enums.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseOperations extends TestUtils {

    public static void navigateTo(URLs page) {
        String url = URLs.getURLValue(page.name());
        String uri = String.format(Constants.BASE_URL + url);
        getDriver().get(uri);
    }

    public static String getFullURL(URLs page) {
        String url = URLs.getURLValue(page.name());
        return String.format(Constants.BASE_URL + url);
    }

    public static void clickElement(WebElement element) {
        WebDriverWait elementWait = new WebDriverWait(TestUtils.getDriver(), Duration.ofSeconds(10));
        elementWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
