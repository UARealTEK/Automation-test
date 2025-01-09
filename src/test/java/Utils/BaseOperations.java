package Utils;

import Enums.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BaseOperations extends DriverOperations {

    public static void navigateTo(URLs page) {
        String url = URLs.getURLValue(page.name());
        String uri = String.format(Constants.BASE_URL + url);
        getDriver().get(uri);
    }

    public static String getFullURL(URLs page) {
        String url = URLs.getURLValue(page.name());
        return String.format(Constants.BASE_URL + url);
    }

    public static WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static void clickElement(WebElement element) {
        WebDriverWait elementWait = new WebDriverWait(DriverOperations.getDriver(), Duration.ofSeconds(10));
        elementWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static String getRandomString(int number) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < number; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }

        return builder.toString();
    }

    public static int getRandomNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(101);
    }

    public static String getJavaScriptPropertyValue(WebElement element, String propertyName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        Object result = jsExecutor.executeScript(String.format("return arguments[0].%s;", propertyName), element);

        if (result instanceof Boolean) {
            return String.valueOf(result);
        } else if (result instanceof String) {
            return (String) result;
        } else if (result != null) {
            return result.toString();
        } else {
            return null;
        }
    }

    public static Boolean isElementVisible(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        return (Boolean) jsExecutor.executeScript(
                "return window.getComputedStyle(arguments[0]).display === 'none';", element);
    }

    public static String getPseudoElementPropertyValue(WebElement element, String pseudoElement, String cssProperty) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String script = String.format("return window.getComputedStyle(arguments[0], '::%s').getPropertyValue('%s');", pseudoElement,cssProperty);
        return (String) jsExecutor.executeScript(script,element);
    }
}
