package helperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class BaseOperations extends TestUtils {
    public static void openPage(String url) {
        getDriver().get(url);
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

    public static WebElement selectRandomPizzaSize() {
        Random random = new Random();

        List<String> pizzaSizes = new ArrayList<>();
        pizzaSizes.add("//*[@id=\"rad_large\"]");
        pizzaSizes.add("//*[@id=\"rad_medium\"]");
        pizzaSizes.add("//*[@id=\"rad_small\"]");
        int randomIndex = random.nextInt(pizzaSizes.size());

        return BaseOperations.locateElementBy(pizzaSizes.get(randomIndex),"xpath");
    }

    public static void selectRandomPizzaFlavour() {
        Random random = new Random();

        WebElement flavourDropdown = BaseOperations.locateElementBy("//*[@id=\"select_flavor\"]","xpath");

        Select flavourSelect = new Select(flavourDropdown);
        List<WebElement> pizzaFlavoursList = flavourSelect.getOptions();

        BaseOperations.clickElement(flavourDropdown);

        int randomPizzaFlavourIndex = random.nextInt(pizzaFlavoursList.size());
        flavourSelect.selectByIndex(randomPizzaFlavourIndex);
    }

    public static WebElement selectRandomSauce() {
        Random random = new Random();

        List<String> saucesList = new ArrayList<>();
        saucesList.add("//*[@id=\"rad_marinara\"]");
        saucesList.add("//*[@id=\"rad_buffalo\"]");
        saucesList.add("//*[@id=\"rad_barbeque\"]");
        int randomSauceIndex = random.nextInt(saucesList.size());

        return BaseOperations.locateElementBy(saucesList.get(randomSauceIndex), "xpath");
    }

    public static void selectRandomToppings(int toppingsAmountToSelect) {
        if (toppingsAmountToSelect > 3) {
            throw new AssertionError("There are only 3 available toppings options. Please insert value from 1 to 3");
        }

        Random random = new Random();

        WebElement toppingsDiv = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[4]/div[2]","xpath");
        List<WebElement> toppingsList = toppingsDiv.findElements(By.tagName("input"));
        Set<WebElement> selectedToppings = new HashSet<>();

        while (selectedToppings.size() < toppingsAmountToSelect) {
            int randomToppingIndex = random.nextInt(toppingsList.size());
            selectedToppings.add(toppingsList.get(randomToppingIndex));
        }

        for (WebElement topping : selectedToppings) {
            topping.click();
        }
    }

    public static void insertAmountOfPizzasToOrder(int pizzasToOrder) {
        WebElement pizzaCount = BaseOperations.locateElementBy("//*[@id=\"quantity\"]","xpath");
        BaseOperations.clickElement(pizzaCount);
        pizzaCount.sendKeys(String.valueOf(pizzasToOrder));
    }
}
