package Pages.FormsPage.Range;

import Utils.BaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RangeField {

    private static final Logger log = LogManager.getLogger(RangeField.class);
    private final WebDriver driver;


    public RangeField(WebDriver driver) {
        this.driver = driver;
    }

    //Fluency level
    private final By fluencyLevelScroll = By.id("fluency");

    //Fluency level Label
    private final By fluencyLevelScrollLabel = By.xpath("//div[@class = 'form-row align-items-center']//label[@for='fluency']");
    private final By fluencyLevelScrollState = By.id("fluency_validate");

    //Range Methods
    public WebElement getRangeElement() {
        return driver.findElement(fluencyLevelScroll);
    }

    public String getRangeElementValue() {
        return BaseOperations.getJavaScriptPropertyValue(getRangeElement(),"value");
    }

    public int getRangeElementWidth() {
        return getRangeElement().getSize().getWidth();
    }

    public int getRangeOptionWidth() throws AttributeNotFoundException {
        return (int) ((double) getRangeElement().getSize().getWidth() / getRangeElementMaxSize());
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
        double rangeWidth = getRangeElementWidth();
        log.debug(rangeWidth);

        Optional<String> optionalOptionsAmount = Optional.ofNullable(range.getAttribute("max"));
        Integer optionsAmount = optionalOptionsAmount.map(Integer::parseInt).orElse(null);
        double optionWidth;

        if (optionsAmount != null) {
            optionWidth = getRangeOptionWidth();
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        BaseOperations.focusOnElement(getRangeElement());
        while (Integer.parseInt(getRangeElementValue()) != getRangeElementMaxSize()) {
            action.sendKeys(Keys.ARROW_RIGHT).perform();
        }
        js.executeScript("document.querySelector('input[id=\"fluency\"]').dataset.interacted = true;");

    }

    public void setRangeToMinOption() throws AttributeNotFoundException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        BaseOperations.focusOnElement(getRangeElement());
        while (Integer.parseInt(getRangeElementValue()) != getRangeElementMinSize()) {
            action.sendKeys(Keys.ARROW_LEFT).perform();
        }
        js.executeScript("document.querySelector('input[id=\"fluency\"]').dataset.interacted = true;");

    }

    public void dragRangeByOffset(int XOffset) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        action
                .moveToElement(getRangeElement())
                .moveByOffset(-(getRangeElementWidth() / 2),0) // click happens at the start of the Range
                .clickAndHold().moveByOffset(XOffset,0)
                .release()
                .perform();
        js.executeScript("document.querySelector('input[id=\"fluency\"]').dataset.interacted = true;");
    }
}
