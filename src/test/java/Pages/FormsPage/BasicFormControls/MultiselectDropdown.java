package Pages.FormsPage.BasicFormControls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class MultiselectDropdown {

    private static final Logger log = LogManager.getLogger(MultiselectDropdown.class);
    private final WebDriver driver;


    public MultiselectDropdown(WebDriver driver) {
        this.driver = driver;
    }

    //Choose Language multiSelect dropdown
    private final By languageSelectMultiDropdown = By.id("select_lang");

    //Choose Language multiSelect dropdown Labels
    private final By languageSelectMultiDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_lang']");
    private final By languageSelectMultiDropdownSelectedOptionsLabel = By.id("select_lang_validate");

    //Multiselect dropdown methods
    public Select getMultiSelectDropdown() {
        return new Select(driver.findElement(languageSelectMultiDropdown));
    }

    public WebElement getSelectedDropdownOptionsLabel() {
        return driver.findElement(languageSelectMultiDropdownSelectedOptionsLabel);
    }

    public int getCountOfSelectedOptions() {
        int count = 0;
        List<WebElement> dropdownOptions = getMultiSelectDropdown().getOptions();
        for (WebElement option : dropdownOptions) {
            if (option.isSelected()) {
                count++;
            }
        }
        return count;
    }

    //Select random options in the multiselect Dropdown
    public void selectRandomOptions(List<WebElement> options, int itemsToSelect) {
        Random random = new Random();

        int optionsToSelect = random.nextInt(itemsToSelect) + 1; // this makes sure that at least 1 option will be selected
        Set<WebElement> selectedOptions = new HashSet<>();

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).perform();

        while(getCountOfSelectedOptions() < optionsToSelect) {
            int randomIndex = random.nextInt(options.size());
            WebElement element = options.get(randomIndex);

            if (!selectedOptions.contains(element) && !element.isSelected()) {
                action.click(element).perform();
                selectedOptions.add(element);
            }
        }

        action.keyUp(Keys.CONTROL).perform(); // Release the CTRL key after selection
    }

    public void deselectedOptionAtIndex(List<WebElement> list, int index) {
        Actions action = new Actions(driver);
        WebElement element = list.get(index);

        if (element.isSelected()) {
            action
                    .keyDown(Keys.CONTROL)
                    .click(element)
                    .keyUp(Keys.CONTROL)
                    .perform();
        } else {
            log.warn("The item was NOT selected at index - {}", index);
        }
    }

    public List<WebElement> getSelectedOptionsList() {
        List<WebElement> list = getMultiSelectDropdown().getOptions();

        List<WebElement> selectedOptions = new ArrayList<>();
        for (WebElement element : list) {
            if (element.isSelected()) {
                selectedOptions.add(element);
            }
        }

        return selectedOptions;
    }
}
