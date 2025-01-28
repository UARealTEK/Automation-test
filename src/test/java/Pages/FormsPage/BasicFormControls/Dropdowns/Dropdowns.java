package Pages.FormsPage.BasicFormControls.Dropdowns;

import Enums.DropdownSelectCriteria;
import Utils.BaseOperations;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Dropdowns {
    private final WebDriver driver;

    public Dropdowns(WebDriver driver) {
        this.driver = driver;
    }

    //Primary skill dropdown
    private static final By primarySkillDropdown = By.id("select_tool");
    private final By primarySkillDropdownOptions = By.xpath("//div[@class = 'form-group']//select[@id='select_tool']//option");

    //Primary skill dropdown Label
    private final By primarySkillDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_tool']");
    private static final By primarySkillDropdownSelectedLabel = By.id("select_tool_validate");

    // Dropdown methods
    public List<WebElement> getDropdownOptionsList() {
        Select options = new Select(driver.findElement(primarySkillDropdown));
        return options.getOptions();
    }

    public Select getDropdown() {
        return new Select(driver.findElement(primarySkillDropdown));
    }

    public String getDropdownText(Select dropdown) {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        return (String) js.executeScript(
                "return arguments[0].options[arguments[0].selectedIndex].text;", dropdown.getWrappedElement());
    }

    public String getDropdownLabel() {
        return driver.findElement(primarySkillDropdownSelectedLabel).getText();
    }

    public void selectRandomDropdownOptionByText(Select dropdown) {
        Random random = new Random();

        List<WebElement> options = dropdown.getOptions();
        List<String> optionsTexts = options.stream()
                .map(WebElement::getText).toList();

        int randomIndex;
        String randomValue;
        WebElement option;

        do {
            randomIndex = random.nextInt(optionsTexts.size());
            randomValue = optionsTexts.get(randomIndex);
            option = options.get(randomIndex);
        } while (option.isSelected());

        dropdown.selectByVisibleText(randomValue);
    }


    public void selectRandomDropdownOptionByValue(Select dropdown) {
        Random random = new Random();

        List<WebElement> options = dropdown.getOptions();
        List<String> optionsValues = options.stream()
                .map(option -> option.getAttribute("value"))
                .toList();

        int randomIndex;
        String randomValue;
        WebElement option;

        do {
            randomIndex = random.nextInt(optionsValues.size());
            randomValue = optionsValues.get(randomIndex);
            option = options.get(randomIndex);
        } while (option.isSelected());

        dropdown.selectByValue(randomValue);
    }

    public void selectRandomDropdownOptionByIndex(Select dropdown) {
        Random random = new Random();

        List<WebElement> options = dropdown.getOptions();

        int randomIndex;
        WebElement option;

        do {
            randomIndex = random.nextInt(options.size());
            option = options.get(randomIndex);
        } while (option.isSelected());

        dropdown.selectByIndex(randomIndex);
    }

    public void selectRandomDropdownOptionBy(Select dropdown, DropdownSelectCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("DropdownSelectCriteria cannot be null");
        }

        switch (criteria) {
            case BY_TEXT -> selectRandomDropdownOptionByText(dropdown);
            case BY_INDEX -> selectRandomDropdownOptionByIndex(dropdown);
            case BY_VALUE -> selectRandomDropdownOptionByValue(dropdown);
            default -> throw new IllegalArgumentException(String.format("The passed in value %s was NOT recognized as a criteria", criteria));
        }

    }
}
