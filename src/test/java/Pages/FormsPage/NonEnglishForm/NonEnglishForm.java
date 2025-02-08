package Pages.FormsPage.NonEnglishForm;

import Utils.BaseOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NonEnglishForm {

    private final WebDriver driver;

    public NonEnglishForm(WebDriver driver) {
        this.driver = driver;
    }

    private final By inputField = By.id("नाव");
    private final By inputFieldLabel = By.xpath("//div[@class='card-body']//label[@for='नाव']");
    private final By inputFieldValidate = By.id("नाव_तपासा");
    private final By checkboxOne = By.id("मराठी");
    private final By checkboxTwo = By.id("ગુજરાતી");
    private final By checkboxThree = By.id("ਪੰਜਾਬੀ");
    private final By checkboxes = By.xpath("//div[@class='card-body']//div[@class='col-sm-6']//form//input");
    private final By checkboxOneLabel = By.xpath("//div[@class='card-body']//label[@for='मराठी']");
    private final By checkboxTwoLabel = By.xpath("//div[@class='card-body']//label[@for='ગુજરાતી']");
    private final By checkboxThreeLabel = By.xpath("//div[@class='card-body']//label[@for='ਪੰਜਾਬੀ']");
    private final By checkboxValidate = By.id("check_validate_non_english");
    private final By checkboxLabels = By.xpath("//div[@class='card-body']//label[string-length(@for) = string-length(translate(@for, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '')) and not(following-sibling::*)]");

    public WebElement getInputField() {
        return driver.findElement(inputField);
    }

    public WebElement getInputFieldLabel() {
        return driver.findElement(inputFieldLabel);
    }

    public WebElement getInputFieldValidate() {
        return driver.findElement(inputFieldValidate);
    }

    public WebElement getCheckboxOne() {
        return driver.findElement(checkboxOne);
    }

    public WebElement getCheckboxTwo() {
        return driver.findElement(checkboxTwo);
    }

    public WebElement getCheckboxThree() {
        return driver.findElement(checkboxThree);
    }

    public WebElement getCheckboxOneLabel() {
        return driver.findElement(checkboxOneLabel);
    }

    public WebElement getCheckboxTwoLabel() {
        return driver.findElement(checkboxTwoLabel);
    }

    public WebElement getCheckboxThreeLabel() {
        return driver.findElement(checkboxThreeLabel);
    }

    public WebElement getCheckboxValidate() {
        return driver.findElement(checkboxValidate);
    }

    public List<WebElement> getCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public List<WebElement> getCheckboxesLabels() {
        return driver.findElements(checkboxLabels);
    }

    public boolean isFieldDefault() {
        return getInputField().getText().isEmpty()
                && getInputFieldValidate().getText().isEmpty();
    }

    public boolean isCheckboxesDefault() {
        return getCheckboxes().stream().noneMatch(WebElement::isSelected) && getCheckboxValidate().getText().isEmpty();
    }

    public boolean isFieldAndLabelMatched() {
        if (getInputField() != null && getInputFieldLabel() != null) {
            String fieldId = getInputField().getAttribute("id");
            String labelFor = getInputFieldLabel().getAttribute("for");

            return fieldId != null && fieldId.equalsIgnoreCase(labelFor);
        }
        return false;
    }

    public boolean isCheckboxesAndLabelMatched() {
        List<WebElement> checkboxes = getCheckboxes();
        List<WebElement> checkboxLabels = getCheckboxesLabels();

        if (checkboxes == null || checkboxLabels == null || checkboxes.size() != checkboxLabels.size()) {
            return false;
        }

        for (int i = 0; i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            WebElement label = checkboxLabels.get(i);

            if (checkbox == null || label == null) {
                return false;
            }

            String checkboxId = checkbox.getAttribute("id");
            String labelFor = label.getAttribute("for");

            if (checkboxId == null || !checkboxId.equalsIgnoreCase(labelFor)) {
                return false;
            }
        }

        return true;
    }

    public void insertDataIntoField(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(getInputField())
                .sendKeys(BaseOperations.getRandomString(BaseOperations.getRandomNumber()))
                .build()
                .perform();
    }

    public boolean isFieldAndValidateMatched() {
        if (Objects.requireNonNull(getInputField().getAttribute("value")).isEmpty() && getInputFieldValidate().getText().isEmpty()) {
            return true;
        }

        else return Objects.requireNonNull(getInputField().getAttribute("value")).equals(getInputFieldValidate().getText());
    }

    public void selectRandomCheckboxes() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<WebElement> checkboxes = getCheckboxes();
        int checkboxesToSelect = random.nextInt(checkboxes.size()) + 1;

        while (checkboxesToSelect != 0) {
            int indexToChoose = random.nextInt(checkboxes.size());
            checkboxes.get(indexToChoose).click();
            checkboxes.remove(indexToChoose);
            checkboxesToSelect--;
        }
    }

    public boolean isCheckboxesAndValidateMatched() {
        String selectedCheckboxesValues = getCheckboxes().stream()
                .filter(WebElement::isSelected)
                .map(checkbox -> checkbox.getAttribute("value"))
                .collect(Collectors.joining(" "));

        if (getCheckboxes().stream().noneMatch(WebElement::isSelected) && getCheckboxValidate().getText().isEmpty()) {
            return true;
        }

        return selectedCheckboxesValues.equals(getCheckboxValidate().getText());
    }
}
