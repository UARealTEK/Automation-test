package Pages;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class OrderPage {
    private WebDriver driver;
    private final By successMessage = By.id("added_message");
    private final By sauceMarinara = By.id("rad_marinara");
    private final By sauceBuffalo = By.id("rad_buffalo");
    private final By sauceBarbeque = By.id("rad_barbeque");

    private final By largeSize = By.id("rad_large");
    private final By mediumSize = By.id("rad_medium");
    private final By smallSize = By.id("rad_small");
    private final By flavourDropdown = By.id("select_flavor");
    private final By pizzaQuantityField = By.id("quantity");
    private final List<WebElement> toppingsList = driver.findElements(By.xpath("//label[contains(text(), 'Toppings')]/ancestor::div[2]//input[@class='form-check-input']"));
    private final int toppingsAmount = toppingsList.size();
    private final By submitButton = By.id("submit_button");
    private final By errorMessageDiv = By.xpath("//*[@id=\"quantity_modal\"]/div/div/div[1]/i");


    OrderPage(WebDriver driver) {

        this.driver = driver;
    }

    public void selectRandomPizzaSize() {
        Random random = new Random();

        List<By> pizzaSizes = new ArrayList<>();
        pizzaSizes.add(largeSize);
        pizzaSizes.add(mediumSize);
        pizzaSizes.add(smallSize);
        int randomIndex = random.nextInt(pizzaSizes.size());

        driver.findElement(pizzaSizes.get(randomIndex)).click();
    }

    public void selectRandomPizzaFlavour() {
        Random random = new Random();

        Select flavourSelect = new Select(driver.findElement(flavourDropdown));
        List<WebElement> pizzaFlavoursList = flavourSelect.getOptions();

        BaseOperations.clickElement(driver.findElement(flavourDropdown));

        int randomPizzaFlavourIndex = random.nextInt(pizzaFlavoursList.size());
        flavourSelect.selectByIndex(randomPizzaFlavourIndex);
    }

    public void selectRandomSauce() {
        Random random = new Random();

        List<By> saucesList = new ArrayList<>();
        saucesList.add(sauceMarinara);
        saucesList.add(sauceBuffalo);
        saucesList.add(sauceBarbeque);
        int randomSauceIndex = random.nextInt(saucesList.size());

        driver.findElement(saucesList.get(randomSauceIndex)).click();
    }

    public void selectRandomToppings(int toppingsAmountToSelect) {
        if (toppingsAmountToSelect > toppingsAmount) {
            throw new AssertionError("There are only 3 available toppings options. Please insert value from 1 to 3");
        }

        Random random = new Random();

        Set<WebElement> selectedToppings = new HashSet<>();

        while (selectedToppings.size() < toppingsAmountToSelect) {
            int randomToppingIndex = random.nextInt(toppingsList.size());
            selectedToppings.add(toppingsList.get(randomToppingIndex));
        }

        for (WebElement topping : selectedToppings) {
            topping.click();
        }
    }

    public void insertAmountOfPizzas(int amountToInsert) {
        driver.findElement(pizzaQuantityField).sendKeys(String.valueOf(amountToInsert));
    }

    public void placeOrder(OrderPage orderPage) {
        Random random = new Random();

        orderPage.selectRandomPizzaSize();
        orderPage.selectRandomPizzaFlavour();
        orderPage.selectRandomSauce();
        orderPage.selectRandomToppings(random.nextInt(toppingsAmount));
        insertAmountOfPizzas(random.nextInt());
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public Boolean isSuccessMessageDisplayed() {
        WebDriverWait waitMessageToAppear = new WebDriverWait(driver, Duration.ofSeconds(10));
        return waitMessageToAppear.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public String getErrorMessage() {

        return driver.findElement(errorMessageDiv).getText();
    }
}
