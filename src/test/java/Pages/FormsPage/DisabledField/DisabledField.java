package Pages.FormsPage.DisabledField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DisabledField {

    private final WebDriver driver;

    //Disabled field
    private final By disabledTextBox = By.id("salary");

    //Disabled field Label
    private final By disabledTextBoxLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='salary']");

    public DisabledField(WebDriver driver) {
        this.driver = driver;
    }
}
