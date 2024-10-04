package Utils;

import Enums.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public static WebElement locateElementBy(String locator, String locatorType) {
        if (locatorType.equalsIgnoreCase("xpath")){
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } else if (locatorType.equalsIgnoreCase("id")) {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
        } else if (locatorType.equalsIgnoreCase("name")) {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
        } else if (locatorType.equalsIgnoreCase("className")) {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
        } else if (locatorType.equalsIgnoreCase("tagName")) {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported operation: %s", locatorType));
        }
    }

    public static void clickElement(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}