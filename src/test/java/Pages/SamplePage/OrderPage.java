package Pages.SamplePage;

import Utils.BaseOperations;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;


@Log4j2
@Getter
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
    private final By toppings = By.xpath("//label[contains(text(), 'Toppings')]/ancestor::div[2]//input[@class='form-check-input']");
    private final By submitButton = By.id("submit_button");
    private final By errorModal = By.id("quantity_modal");
    private final By errorMessageButton = By.xpath("//button[@class='btn btn-warning' and @type='button']");
    private final By loaderPopup = By.id("success_modal");
    private final Duration waitTime = Duration.ofSeconds(10);

    private List<WebElement> toppingList = null;


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

    private void getToppingsList() {
         toppingList = driver.findElements(toppings);
    }

    public void selectRandomToppings(int toppingsAmountToSelect) {
        getToppingsList();
        if (toppingsAmountToSelect > toppingList.size()) {
            throw new AssertionError("There are only 3 available toppings options. Please insert value from 1 to 3");
        }

        Random random = new Random();

        Set<WebElement> selectedToppings = new HashSet<>();

        while (selectedToppings.size() < toppingsAmountToSelect) {
            int randomToppingIndex = random.nextInt(toppingList.size());
            selectedToppings.add(toppingList.get(randomToppingIndex));
        }

        for (WebElement topping : selectedToppings) {
            topping.click();
        }
    }

    public void insertAmountOfPizzas(int amountToInsert) {
        driver.findElement(pizzaQuantityField).sendKeys(String.valueOf(amountToInsert));
    }

    public void placeOrder(OrderPage orderPage) {
        getToppingsList();
        Random random = new Random();

        orderPage.selectRandomPizzaSize();
        orderPage.selectRandomPizzaFlavour();
        orderPage.selectRandomSauce();
        orderPage.selectRandomToppings(random.nextInt(toppingList.size()));
        insertAmountOfPizzas(random.nextInt(10) + 1);
        driver.findElement(submitButton).click();
    }

    public void placeInvalidOrder(OrderPage orderPage) {
        getToppingsList();
        Random random = new Random();

        orderPage.selectRandomPizzaSize();
        orderPage.selectRandomPizzaFlavour();
        orderPage.selectRandomSauce();
        orderPage.selectRandomToppings(random.nextInt(toppingList.size()));
        insertAmountOfPizzas(random.nextInt(-1000,0));
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public Boolean isSuccessMessageDisplayed() {
        WebDriverWait waitMessageToAppear = new WebDriverWait(driver, waitTime);
        return waitMessageToAppear.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public Boolean isSuccessMessageDisappeared() {
        WebDriverWait waitMessageToAppear = new WebDriverWait(driver, waitTime);

        try {
            return waitMessageToAppear.until(ExpectedConditions.invisibilityOfElementLocated(successMessage));
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getErrorMessage() {
        WebDriverWait errorMessageWait = new WebDriverWait(driver,waitTime);
        errorMessageWait.until(ExpectedConditions.visibilityOfElementLocated(errorModal));
        return driver.findElement(errorModal).findElement(By.className("modal-body")).getText();
    }
    public boolean isErrorMessageDisplayed() {
        WebDriverWait popupWait = new WebDriverWait(driver,waitTime);
        try {
            popupWait.until(ExpectedConditions.attributeToBe(errorModal,"style","padding-right: 17px; display: block;"));
        } catch (TimeoutException e) {

        }
        return driver.findElement(errorModal).getAttribute("style").equals("padding-right: 17px; display: block;");
    }

    public void closeErrorModal() throws InterruptedException {
            WebElement button = driver.findElement(errorMessageButton);
            button.click();
            if (button.isDisplayed()) {
                Thread.sleep(2000);
            }
    }

    public String getLoaderMessage() {
        WebDriverWait loaderWait = new WebDriverWait(driver,waitTime);
        loaderWait.until(ExpectedConditions.visibilityOfElementLocated(loaderPopup));
        return driver.findElement(loaderPopup).findElement(By.cssSelector(".col.col-sm-10")).getText();
    }

    public boolean isLoaderDisplayed() {
        WebDriverWait popupWait = new WebDriverWait(driver,waitTime);
        try {
            popupWait.until(ExpectedConditions.attributeToBe(loaderPopup,"style","padding-right: 17px; display: block;"));

        } catch (TimeoutException e) {

        }
        return driver.findElement(loaderPopup).getAttribute("style").equals("padding-right: 17px; display: block;");
    }

}
