package Pages.FormsPage.BasicFormControls;

import Utils.BaseOperations;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class YearsOfExperience {

    private final WebDriver driver;

    public YearsOfExperience(WebDriver driver) {
        this.driver = driver;
    }

    private final By YearsOfExperienceFieldLabel = By.id("exp_help");
    private final By yearsOfExperienceField = By.id("exp");

    @Getter
    private final String expectedYearsOfExperiencePlaceholder = "years of automation experience";

    public WebElement getYearsOfExperienceField() {
        return driver.findElement(yearsOfExperienceField);
    }
    public String getYearsOfExperiencePlaceholder() {
        return driver.findElement(yearsOfExperienceField).getAttribute("placeholder");
    }

    public String getYearsOfExperienceInsertedValue() {
        return driver.findElement(yearsOfExperienceField).getAttribute("value");
    }

    public void insertDataIntoYearsOfExperienceField() {
        Actions action = new Actions(driver);
        action
                .click(getYearsOfExperienceField())
                .sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()));
    }

    public String getYearsOfExperienceFieldLabel() {
        return driver.findElement(YearsOfExperienceFieldLabel).getText();
    }

}
