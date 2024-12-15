package Pages.FormsPage;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Do I need to add labels for each separate dropdown option?? (Primary Skill)
 */

public class FormsPage {
    //WebDriver
    private final WebDriver driver;

    //Basic Form Controls ===

    //Fields
    @Getter
    private final By yearsOfExperienceField = By.id("exp");

    //Fields Labels
    private final By YearsOfExperienceFieldLabel = By.id("exp_help");

    //Checkboxes
    private final By BFCCheckboxes = By.xpath("//div[@class = 'form-group']//input[@type='checkbox'] [starts-with(@id,'check')]"); // Contains java / python / javaScript checkboxes
    private final By pythonCheckBox = By.id("check_python");
    private final By javaCheckBox = By.id("check_java");
    private final By javaScriptCheckBox = By.id("check_javascript");

    //Checkbox Labels
    private final By BFCCheckboxesLabels = By.xpath("//div[@class = 'form-group']//label[starts-with(@for,'check')]"); // Contains java / python / javaScript checkbox labels
    private final By BFCSelectedCheckboxLabel = By.id("check_validate"); // Label for the selected checkboxes
    private final By pythonLabel = By.xpath("//label[@for='check_python']");
    private final By javaLabel = By.xpath("//label[@for='check_java']");
    private final By javaScriptLabel = By.xpath("//label[@for='check_javascript']");

    //Radiobuttons
    private final By BFCRadiobuttons = By.xpath("//div[@class = 'form-group']//input[@type='radio']"); // Contains all radiobuttons
    private final By seleniumRadioButton = By.id("rad_selenium");
    private final By protractorRadiobutton = By.id("rad_protractor");

    //Radiobuttons Labels
    private final By BFCRadiobuttonsLabel = By.id("rad_validate");
    private final By seleniumLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_selenium']");
    private final By protractorLabel = By.xpath("//div[@class = 'form-group']//label[@for='rad_protractor']");

    //Primary skill dropdown
    private final By primarySkillDropdown = By.id("select_tool");
    private final By primarySkillDropdownOptions = By.xpath("//div[@class = 'form-group']//select[@id='select_tool']//option");

    //Primary skill dropdown Label
    private final By primarySkillDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_tool']");
    private final By primarySkillDropdownSelectedLabel = By.id("select_tool_validate");

    //Choose Language multiSelect dropdown
    private final By languageSelectMultiDropdown = By.id("select_lang");
    private final By languageSelectMultiDropdownOptions = By.xpath("//div[@class = 'form-group']//select[@id='select_lang']//option");

    //Choose Language multiSelect dropdown Labels
    private final By languageSelectMultiDropdownLabel = By.xpath("//div[@class = 'form-group']//label[@for='select_lang']");
    private final By languageSelectMultiDropdownSelectedOptionsLabel = By.id("select_lang_validate");

    //Notes field
    private final By notesField = By.id("notes");

    //Notes field Label
    private final By notesFieldLabel = By.id("area_notes_validate");

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

    public void insertDataIntoYearsOfExperienceField(By locator) {
        WebElement element = BaseOperations.getElement(getYearsOfExperienceField());
        BaseOperations.clickElement(element);
        element.sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()));
    }

    public String getYearsOfExperienceFieldLabel() {
        return driver.findElement(YearsOfExperienceFieldLabel).getText();
    }

    //Checkboxes methods
    public List<WebElement> getListOfCheckboxes() {
        return driver.findElements(BFCCheckboxes);
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










}
