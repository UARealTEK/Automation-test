package Pages.FormsPage;

import Enums.DropdownSelectCriteria;
import Utils.BaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import lombok.Getter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

/**
work on "selectRandomDropdownOptionBy... method"
 Need to work it with different selection arguments (Text / Index / Value)
 */

public class FormsPage {
    private static final Logger log = LogManager.getLogger(FormsPage.class);
    //WebDriver
    private final WebDriver driver;

    //Basic Form Controls ===

    //Fields
    @Getter
    private final By yearsOfExperienceField = By.id("exp");
    @Getter
    private static final String expectedFreeTextAreaPlaceholder = "Notes";

    //Fields Labels
    private final By YearsOfExperienceFieldLabel = By.id("exp_help");

    //Checkboxes
    private static final By BFCCheckboxes = By.xpath("//div[@class = 'form-group']//input[@type='checkbox'] [starts-with(@id,'check')]"); // Contains java / python / javaScript checkboxes
    private final By pythonCheckBox = By.xpath("//div[@class = 'form-group']//span[@id='check_validate']");
    private final By javaCheckBox = By.id("check_java");
    private final By javaScriptCheckBox = By.id("check_javascript");

    //Checkbox Labels
    private final static By BFCCheckboxesLabels = By.xpath("//div[@class = 'form-group']//label[starts-with(@for,'check')]"); // Contains java / python / javaScript checkbox labels
    private final By BFCSelectedCheckboxLabel = By.xpath("//div[@class = 'form-group']//span[@id='check_validate']"); // Label for the selected checkboxes

    //Radiobuttons
    private static final By BFCRadiobuttons = By.xpath("//div[@class = 'form-group']//input[@type='radio']"); // Contains all radiobuttons
    private final By seleniumRadioButton = By.id("rad_selenium");
    private final By protractorRadiobutton = By.id("rad_protractor");

    //Radiobuttons Labels
    private static final By BFCRadiobuttonsLabel = By.xpath("//div[@class = 'form-group']//label[starts-with(@for,'rad')]");
    private final By BFCRadiobuttonsSelectedLabel = By.id("rad_validate");
    private final By seleniumLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_selenium']");
    private final By protractorLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_protractor']");

    //Primary skill dropdown
    private static final By primarySkillDropdown = By.id("select_tool");
    private final By primarySkillDropdownOptions = By.xpath("//div[@class = 'form-group']//select[@id='select_tool']//option");

    //Primary skill dropdown Label
    private final By primarySkillDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_tool']");
    private static final By primarySkillDropdownSelectedLabel = By.id("select_tool_validate");

    //Choose Language multiSelect dropdown
    private static final By languageSelectMultiDropdown = By.id("select_lang");

    //Choose Language multiSelect dropdown Labels
    private final By languageSelectMultiDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_lang']");
    private final By languageSelectMultiDropdownSelectedOptionsLabel = By.id("select_lang_validate");

    //Notes field
    private static final By notesField = By.id("notes");

    //Notes field Label
    private static final By notesFieldLabel = By.id("area_notes_validate");

    //Mandatory field
    private final By mandatoryField = By.id("common_sense");

    //Mandatory field Label
    private final By mandatoryFieldLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='common_sense']");

    //Switch checkbox
    private final By switchBoxButton = By.id("german");

    //Switch checkbox Label
    private final By switchBoxButtonLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='german']");
    private final By switchBoxButtonState = By.id("german_validate");

    //Fluency level
    private final By fluencyLevelScroll = By.id("fluency");

    //Fluency level Label
    private final By fluencyLevelScrollLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='fluency']");
    private final By fluencyLevelScrollState = By.id("fluency_validate");

    //Upload CV (Single File)
    private final By uploadCVButtonSingle = By.id("upload_cv");

    //Upload CV (Single File) Label
    private final By uploadCVSingleState = By.id("validate_cv");
    private final By uploadCVSingleLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='upload_cv']");

    //Upload Certificates (Multiple Files)
    private final By UploadCertificate = By.id("upload_files");

    //Upload Certificates (Multiple Files) Label
    private final By uploadCertificateState = By.id("validate_files");
    private final By uploadCertificateLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='upload_files']");

    //Download file
    private final By downloadFile = By.id("download_file");

    //Download file Label
    private final By downloadFileLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='download_file']");

    //Disabled field
    private final By disabledTextBox = By.id("salary");

    //Disabled field Label
    private final By disabledTextBoxLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='salary']");

    //Constructor
    public FormsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Expected values
    @Getter
    public final String expectedYearsOfExperiencePlaceholder = "years of automation experience";

    //Years Of Experience Methods
    public String getYearsOfExperiencePlaceholder() {
        return driver.findElement(yearsOfExperienceField).getAttribute("placeholder");
    }

    public String getYearsOfExperienceInsertedValue() {
        return driver.findElement(yearsOfExperienceField).getAttribute("value");
    }

    public void insertDataIntoYearsOfExperienceField() {
        WebElement element = BaseOperations.getElement(getYearsOfExperienceField());
        BaseOperations.clickElement(element);
        element.sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()));
    }

    public String getYearsOfExperienceFieldLabel() {
        return driver.findElement(YearsOfExperienceFieldLabel).getText();
    }

    //Checkboxes methods
    public static List<WebElement> getListOfCheckboxes() {
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
        List<WebElement> allCheckboxes = FormsPage.getListOfCheckboxes();

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

    public static List<WebElement> getListOfCheckboxLabels() {
       return BaseOperations.getDriver().findElements(BFCCheckboxesLabels);
    }

    //Radiobuttons methods
    public static List<WebElement> getListOfRadioButtons() {
        return BaseOperations.getDriver().findElements(BFCRadiobuttons);
    }

    public static List<WebElement> getListOfRadioButtonLabels() {
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

    //Multiselect dropdown methods
    public static Select getMultiSelectDropdown() {
        return new Select(BaseOperations.getDriver().findElement(languageSelectMultiDropdown));
    }

    public WebElement getSelectedDropdownOptionsLabel() {
        return driver.findElement(languageSelectMultiDropdownSelectedOptionsLabel);
    }

    public static int getCountOfSelectedOptions() {
        int count = 0;
        List<WebElement> dropdownOptions = FormsPage
                .getMultiSelectDropdown()
                .getOptions();
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
        List<WebElement> list = FormsPage
                .getMultiSelectDropdown()
                .getOptions();

        List<WebElement> selectedOptions = new ArrayList<>();
        for (WebElement element : list) {
            if (element.isSelected()) {
                selectedOptions.add(element);
            }
        }

        return selectedOptions;
    }

    // Dropdown methods
    public static List<WebElement> getDropdownOptionsList() {
        Select options = new Select(BaseOperations.getDriver().findElement(primarySkillDropdown));
        return new ArrayList<>(options.getOptions());
    }

    public static Select getDropdown() {
        return new Select(BaseOperations.getDriver().findElement(primarySkillDropdown));
    }

    public static String getDropdownText(Select dropdown) {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        return (String) js.executeScript(
                "return arguments[0].options[arguments[0].selectedIndex].text;", dropdown.getWrappedElement());
    }

    public static String getDropdownLabel() {
        return BaseOperations.getDriver().findElement(primarySkillDropdownSelectedLabel).getText();
    }

    public static void selectRandomDropdownOptionByText(Select dropdown) {
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


    public static void selectRandomDropdownOptionByValue(Select dropdown) {
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

    public static void selectRandomDropdownOptionByIndex(Select dropdown) {
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

    public static void selectRandomDropdownOptionBy(Select dropdown, DropdownSelectCriteria criteria) {
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

    public static WebElement getHoveredElement() {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        return  (WebElement) js.executeScript(
                "return document.querySelector(':hover');");
    }

    public static void navigateAndSelectNextOption(Actions action) {
            action
                    .keyDown(Keys.ARROW_DOWN)
                    .keyUp(Keys.ARROW_DOWN)
                    .keyDown(Keys.ENTER)
                    .keyUp(Keys.ENTER)
                    .perform();
    }

    //Free text input field methods
    public static WebElement getTextAreaField() {
        return BaseOperations.getDriver().findElement(notesField);
    }

    public static String getTextAreaInsertedText() {
        return getTextAreaField().getAttribute("value");
    }

    public static String getTextAreaFieldLabel() {
        return BaseOperations.getDriver().findElement(notesFieldLabel).getText();
    }

    public static String getTextAreaFieldPlaceholder() {
        return getTextAreaField().getAttribute("placeholder");
    }

    public static void insertRandomTextIntoField(WebElement textField) {
        textField.click();
        textField.sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()));
    }

    public static boolean isPlaceholderVisible(WebElement textArea) {
        String placeholderCssDisplay = BaseOperations.getPseudoElementPropertyValue(textArea, "placeholder", "display");
        return !placeholderCssDisplay.equals("none");
    }




















}
