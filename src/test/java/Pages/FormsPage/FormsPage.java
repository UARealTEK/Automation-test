package Pages.FormsPage;

import Enums.DropdownSelectCriteria;
import Utils.BaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import lombok.Getter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.management.AttributeNotFoundException;
import java.util.*;

public class FormsPage {
    private static final Logger log = LogManager.getLogger(FormsPage.class);

    //WebDriver
    private final WebDriver driver;

    //Fields
    @Getter
    private static final String expectedFreeTextAreaPlaceholder = "Notes";

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

    //ReadOnly field
    private static final By readonlyField = By.id("common_sense");

    //Mandatory field Label
    private final By readonlyFieldLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='common_sense']");

    //Switch checkbox
    private final By switchBoxButton = By.id("german");

    //Switch checkbox Label
    private final By switchBoxLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='german']");
    private final By switchBoxValidate = By.id("german_validate");

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
    public final String expectedReadOnlyPlaceholder = "Common Sense";

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

    public static void resizeTextAreaViaJS(WebElement element, WebDriver driver, int specifiedHeight) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int elementHeight = element.getSize().getHeight();
        String script = "arguments[0].style.height = arguments[1] + 'px';";
        // Resize the textarea by setting height and width directly
        js.executeScript(script, element, elementHeight + specifiedHeight);
    }

    public static int getTextAreaScrollHeight(WebElement textArea) {
        Optional<String> scrollHeight = Optional.ofNullable(textArea.getAttribute("scrollHeight"));
        return scrollHeight.map(Integer::parseInt).orElse(0);
    }

    public static int getTextAreaClientHeight(WebElement textArea) {
        Optional<String> clientHeight = Optional.ofNullable(textArea.getAttribute("clientHeight"));
        return clientHeight.map(Integer::parseInt).orElse(0);
    }

    public static boolean hasVerticalScrollbar(WebElement textArea) {
        Optional<String> scrollHeight = Optional.ofNullable(textArea.getAttribute("scrollHeight"));
        Optional<String> clientHeight = Optional.ofNullable(textArea.getAttribute("clientHeight"));
        if (scrollHeight.isEmpty() || clientHeight.isEmpty()) {
            return false;
        } else return Integer.parseInt(scrollHeight.get()) > Integer.parseInt(clientHeight.get());
    }

    //Read-only textbox methods
    public static WebElement getReadOnlyTextbox() {
        return BaseOperations.getDriver().findElement(readonlyField);
    }

    public static String getReadOnlyTextboxPlaceholder() {
        return getReadOnlyTextbox().getAttribute("placeholder");
    }

    //Toggle switch methods
    public WebElement getToggleSwitch() {
        return BaseOperations.getDriver().findElement(switchBoxButton);
    }

    public WebElement getToggleSwitchLabelElement() {
        return BaseOperations.getDriver().findElement(switchBoxLabel);
    }

    public boolean isToggleSwitchChecked() {
        return Optional.ofNullable(getToggleSwitch().getAttribute("checked")).isPresent();
    }

    public String getToggleSwitchValidateLabel() {
        return BaseOperations.getDriver().findElement(switchBoxValidate).getText();
    }

    public boolean isToggleSwitchLabelMatched() {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        Object firstInteraction = js.executeScript("return window.firstInteractionDone;");
        boolean isSelected = getToggleSwitch().isSelected();
        String labelValue = getToggleSwitchValidateLabel();
        Optional<String> toggleStateValue = Optional.ofNullable(getToggleSwitch().getAttribute("checked"));

        if (firstInteraction == null && !isSelected && toggleStateValue.isEmpty()) {
            return labelValue.isEmpty();
        } else if (toggleStateValue.isPresent() && isSelected) {
            return labelValue.equals(toggleStateValue.get());
        }

        return false;
    }

    //Range Methods
    public WebElement getRangeElement() {
        return driver.findElement(fluencyLevelScroll);
    }

    public String getRangeElementValue() {
        return BaseOperations.getJavaScriptPropertyValue(getRangeElement(),"value");
    }

    public int getRangeElementMaxSize() throws AttributeNotFoundException {
        Optional<String> maxSize = Optional.ofNullable(BaseOperations.getJavaScriptPropertyValue(getRangeElement(),"max"));
        if (maxSize.isEmpty()) {
            throw new AttributeNotFoundException("Max attribute value is absent");
        } else {
            return Integer.parseInt(maxSize.get());
        }
    }

    public int getRangeElementMinSize() throws AttributeNotFoundException {
        Optional<String> minSize = Optional.ofNullable(BaseOperations.getJavaScriptPropertyValue(getRangeElement(),"min"));
        if (minSize.isEmpty()) {
            throw new AttributeNotFoundException("Min attribute value is absent");
        } else {
            return Integer.parseInt(minSize.get());
        }
    }

    public String getDefaultRangeElementValue() {
        Optional<String> minValue = Optional.ofNullable(getRangeElement().getAttribute("min"));
        Optional<String> maxValue = Optional.ofNullable(getRangeElement().getAttribute("max"));
        Integer minRangeValue;
        Integer maxRangeValue;

        minRangeValue = minValue.map(Integer::parseInt).orElse(null);
        maxRangeValue = maxValue.map(Integer::parseInt).orElse(null);

        if (minRangeValue != null && maxRangeValue != null) {
            int roundedUpValue = (int) Math.round((minRangeValue + maxRangeValue) / 2.0);
            return String.valueOf (roundedUpValue);
        } else return null;
    }

    public String getRangeLabel() {
        return driver.findElement(fluencyLevelScrollState).getText();
    }

    public WebElement getRangeTitle() {
        return driver.findElement(fluencyLevelScrollLabel);
    }

    public boolean isRangeLabelMatched() {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        Object interaction = js.executeScript(
                "return document.querySelector('input[id=\"fluency\"]').dataset.interacted;"
        );
        String labelValue = getRangeLabel();
        String rangeValue = getRangeElementValue();

        if (interaction == null) {
            return labelValue.isEmpty();  // Ensure the label is empty initially
        } else {
            return labelValue.equals(rangeValue);  // Compare label and value after interaction
        }
    }

    public void changeRangeViaMouseInteraction(int rangeValue) throws AttributeNotFoundException {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        js.executeScript(
                "var slider = document.querySelector('input[id=\"fluency\"]');" +
                        "slider.addEventListener('input', function() {" +
                        "  slider.dataset.interacted = true;" +
                        "});"
        );

        Actions action = new Actions(driver);
        WebElement range = getRangeElement();
        double rangeWidth = range.getSize().getWidth();
        log.debug(rangeWidth);

        Optional<String> optionalOptionsAmount = Optional.ofNullable(range.getAttribute("max"));
        Integer optionsAmount = optionalOptionsAmount.map(Integer::parseInt).orElse(null);
        double optionWidth;

        if (optionsAmount != null) {
            optionWidth = rangeWidth / optionsAmount;
        } else throw new AttributeNotFoundException("max attribute was not found");
        log.debug(optionWidth);
        log.debug(optionWidth * 5);

        List<Double> options = new ArrayList<>();

        for (int i = 0; i < optionsAmount; i++) {
            options.add(optionWidth * (i + 1));
        }

        if (rangeValue < 0 || rangeValue > options.size()) {
            throw new IndexOutOfBoundsException(String.format("Specified value: %s is not in a valid range", rangeValue));
        }
        double offsetToClick = optionWidth * rangeValue;
        if (rangeValue == optionsAmount) {
            // Avoid the edge case where the click happens exactly at the end
            offsetToClick -= 2;
        } else if (offsetToClick == 0) {
            // Avoid the edge case where the click happens exactly at the start
            offsetToClick += 2;
        }

        action
                .moveToElement(range)
                .moveByOffset(-((int)rangeWidth / 2),0)
                .moveByOffset((int) offsetToClick,0)
                .click()
                .perform();

        String postInteractionValue = getDefaultRangeElementValue();

        if (postInteractionValue.equals(getRangeElementValue())) {
            // If the value hasn't changed, force the `interacted` flag to true
            js.executeScript("document.querySelector('input[id=\"fluency\"]').dataset.interacted = true;");
        }
    }

    public void changeRangeViaKeyboard(int rangeValue) throws AttributeNotFoundException {
        JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
        js.executeScript(
                "var slider = document.querySelector('input[id=\"fluency\"]');" +
                        "slider.addEventListener('input', function() {" +
                        "  slider.dataset.interacted = true;" +
                        "});"
        );

        Actions action = new Actions(driver);
        WebElement rangeLabel = getRangeTitle();

        action.click(rangeLabel).perform();

        if (rangeValue < 0 || rangeValue > getRangeElementMaxSize()) {
            throw new IndexOutOfBoundsException(String.format("Specified value: %s is not in a valid range", rangeValue));
        }

        int movesToMake = rangeValue - Integer.parseInt(getDefaultRangeElementValue());

        while (movesToMake != 0) {
            if (movesToMake > 0) {
                action.sendKeys(Keys.ARROW_RIGHT).perform();
                movesToMake--;
            } else {
                action.sendKeys(Keys.ARROW_LEFT).perform();
                movesToMake++;
            }
        }

        String postInteractionValue = getDefaultRangeElementValue();

        if (postInteractionValue.equals(getRangeElementValue())) {
            // If the value hasn't changed, force the `interacted` flag to true
            js.executeScript("document.querySelector('input[id=\"fluency\"]').dataset.interacted = true;");
        }
    }

    public void setRangeToMaxOption() throws AttributeNotFoundException {
        Actions action = new Actions(driver);

        while (Integer.parseInt(getRangeElementValue()) != getRangeElementMaxSize()) {
            action.sendKeys(Keys.ARROW_RIGHT).perform();
        }
    }

    public void setRangeToMinOption() throws AttributeNotFoundException {
        Actions action = new Actions(driver);

        while (Integer.parseInt(getRangeElementValue()) != getRangeElementMinSize()) {
            action.sendKeys(Keys.ARROW_LEFT).perform();
        }
    }



















}
