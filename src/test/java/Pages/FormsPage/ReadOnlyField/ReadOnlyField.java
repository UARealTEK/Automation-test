package Pages.FormsPage.ReadOnlyField;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReadOnlyField {

    private final WebDriver driver;

    //ReadOnly field
    private final By readonlyField = By.id("common_sense");

    //Mandatory field Label
    private final By readonlyFieldLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='common_sense']");
    @Getter
    private final String expectedReadOnlyPlaceholder = "Common Sense";

    public ReadOnlyField(WebDriver driver) {
        this.driver = driver;
    }

    //Read-only textbox methods
    public WebElement getReadOnlyTextbox() {
        return driver.findElement(readonlyField);
    }

    public String getReadOnlyTextboxPlaceholder() {
        return getReadOnlyTextbox().getAttribute("placeholder");
    }
}
