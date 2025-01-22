package Pages.FormsPage.Radiobutton;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class Radiobuttons {
    private final WebDriver driver;

    public Radiobuttons(WebDriver driver) {
        this.driver = driver;
    }

    private static final By BFCRadiobuttons = By.xpath("//div[@class = 'form-group']//input[@type='radio']"); // Contains all radiobuttons
    private final By seleniumRadioButton = By.id("rad_selenium");
    private final By protractorRadiobutton = By.id("rad_protractor");

    private static final By BFCRadiobuttonsLabel = By.xpath("//div[@class = 'form-group']//label[starts-with(@for,'rad')]");
    private final By BFCRadiobuttonsSelectedLabel = By.id("rad_validate");
    private final By seleniumLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_selenium']");
    private final By protractorLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_protractor']");

    //Radiobuttons methods
    public List<WebElement> getListOfRadioButtons() {
        return BaseOperations.getDriver().findElements(BFCRadiobuttons);
    }

    public List<WebElement> getListOfRadioButtonLabels() {
        return BaseOperations.getDriver().findElements(BFCRadiobuttonsLabel);
    }

    public String getSelectedRadiobuttonsLabel() {
        return driver.findElement(BFCRadiobuttonsSelectedLabel).getText();
    }

    public void selectRandomRadiobutton() {
        Random random = new Random();
        List<WebElement> radiobuttons = getListOfRadioButtons();

        int randomIndex = random.nextInt(radiobuttons.size());
        BaseOperations.clickElement(radiobuttons.get(randomIndex));
    }
}
