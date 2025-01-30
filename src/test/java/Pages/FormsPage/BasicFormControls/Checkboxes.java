package Pages.FormsPage.BasicFormControls;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Checkboxes {
    private final WebDriver driver;

    public Checkboxes(WebDriver driver) {
        this.driver = driver;
    }

    private static final By BFCCheckboxes = By.xpath("//div[@class = 'form-group']//input[@type='checkbox'] [starts-with(@id,'check')]"); // Contains java / python / javaScript checkboxes
    private final By pythonCheckBox = By.xpath("//div[@class = 'form-group']//span[@id='check_validate']");
    private final By javaCheckBox = By.id("check_java");
    private final By javaScriptCheckBox = By.id("check_javascript");

    private final static By BFCCheckboxesLabels = By.xpath("//div[@class = 'form-group']//label[starts-with(@for,'check')]"); // Contains java / python / javaScript checkbox labels
    private final By BFCSelectedCheckboxLabel = By.xpath("//div[@class = 'form-group']//span[@id='check_validate']"); // Label for the selected checkboxes

    public List<WebElement> getListOfCheckboxes() {
        return BaseOperations.getDriver().findElements(BFCCheckboxes);
    }

    public String getSelectedCheckboxLabels() {
        return driver.findElement(BFCSelectedCheckboxLabel).getText();
    }

    public WebElement getPythonCheckbox() {
        return driver.findElement(pythonCheckBox);
    }

    public WebElement getJavaCheckbox() {
        return driver.findElement(javaCheckBox);
    }

    public WebElement getJavaScriptCheckbox() {
        return driver.findElement(javaScriptCheckBox);
    }

    public List<WebElement> selectRandomCheckboxes() {
        Random random = new Random();
        List<WebElement> allCheckboxes = getListOfCheckboxes();

        List<WebElement> activeCheckboxes = new ArrayList<>();

        // Filter out disabled checkboxes
        for (WebElement element : allCheckboxes) {
            if (element.isEnabled()) {
                activeCheckboxes.add(element);
            }
        }

        int itemsToSelect = random.nextInt(activeCheckboxes.size()) + 1;  // Ensure at least one checkbox is selected

        Set<WebElement> output = new HashSet<>();

        // Keep selecting random checkboxes until we get the desired count
        while (output.size() < itemsToSelect) {
            int randomIndex = random.nextInt(activeCheckboxes.size());
            output.add(activeCheckboxes.get(randomIndex)); // Adds the element, automatically handles duplicates
        }

        return new ArrayList<>(output);  // Convert Set to List to return
    }

    public List<WebElement> getListOfCheckboxLabels() {
        return BaseOperations.getDriver().findElements(BFCCheckboxesLabels);
    }
}
