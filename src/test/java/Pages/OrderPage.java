package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO:
// describe all fields within the page
// describe all interactions with fields (e.g for selecting a random sauce)
// getSuccessMessage method
// geErrorMessage method
// describe selecting a random value in each set of preferences

public class OrderPage {
    private WebDriver driver;
    private By sauceMarinara = By.id("rad_marinar");
    private By sauceBuffalo = By.id("rad_buffalo");
    private By sauceBerbeque = By.id("rad_barbeque");


    OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void randomSauce() {
        Random random = new Random();

        List<By> saucesList = new ArrayList<>();
        saucesList.add(sauceMarinara);
        saucesList.add(sauceBuffalo);
        saucesList.add(sauceBerbeque);
        int randomSauceIndex = random.nextInt(saucesList.size());

        driver.findElement(saucesList.get(randomSauceIndex)).click();
    }
}
