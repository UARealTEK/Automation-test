package Pages.FormsPage.BasicFormControls.FreeTextArea;

import Utils.BaseOperations;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class FreeTextArea {

    private final WebDriver driver;

    public FreeTextArea(WebDriver driver) {
        this.driver = driver;
    }

    private final By notesField = By.id("notes");
    private final By notesFieldLabel = By.id("area_notes_validate");
    @Getter
    private final String expectedFreeTextAreaPlaceholder = "Notes";

    //Free text input field methods
    public WebElement getTextAreaField() {
        return driver.findElement(notesField);
    }

    public String getTextAreaInsertedText() {
        return getTextAreaField().getAttribute("value");
    }

    public String getTextAreaFieldLabel() {
        return driver.findElement(notesFieldLabel).getText();
    }

    public String getTextAreaFieldPlaceholder() {
        return getTextAreaField().getAttribute("placeholder");
    }

    public boolean isPlaceholderVisible(WebElement textArea) {
        String placeholderCssDisplay = BaseOperations.getPseudoElementPropertyValue(textArea, "placeholder", "display");
        return !placeholderCssDisplay.equals("none");
    }

    public void resizeTextAreaViaJS(WebElement element, WebDriver driver, int specifiedHeight) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int elementHeight = element.getSize().getHeight();
        String script = "arguments[0].style.height = arguments[1] + 'px';";
        // Resize the textarea by setting height and width directly
        js.executeScript(script, element, elementHeight + specifiedHeight);
    }

    public int getTextAreaScrollHeight(WebElement textArea) {
        Optional<String> scrollHeight = Optional.ofNullable(textArea.getAttribute("scrollHeight"));
        return scrollHeight.map(Integer::parseInt).orElse(0);
    }

    public int getTextAreaClientHeight(WebElement textArea) {
        Optional<String> clientHeight = Optional.ofNullable(textArea.getAttribute("clientHeight"));
        return clientHeight.map(Integer::parseInt).orElse(0);
    }

    public boolean hasVerticalScrollbar(WebElement textArea) {
        Optional<String> scrollHeight = Optional.ofNullable(textArea.getAttribute("scrollHeight"));
        Optional<String> clientHeight = Optional.ofNullable(textArea.getAttribute("clientHeight"));
        if (scrollHeight.isEmpty() || clientHeight.isEmpty()) {
            return false;
        } else return Integer.parseInt(scrollHeight.get()) > Integer.parseInt(clientHeight.get());
    }
}
