package WaitConditions.ElementsVisibilityTests;

import SamplePages.Helper.BaseOperations;
import WaitConditions.Helper.ElementVisibilityBaseOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TBD: to solve the issue with Button triggering (69 row)

public class ElementsToBeVisibleTests extends BaseOperations {
    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isVisibleElement(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> isVisibleElement(7,10)));
    }

    @TestFactory
    public List<DynamicTest> exampleTestFactory2() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> buttonClickFlow(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> buttonClickFlow(7,10)));
    }


    public void isVisibleElement(int min, int max) {
        SoftAssertions soft = new SoftAssertions();
        ElementVisibilityBaseOperations.triggerElement(min,max);

        boolean elementIsVisible = false;
        String appearedSignLocator = "//*[@id=\"visibility_target\"]";

        WebDriverWait smallWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds((long) (min - 0.0001)));
        try {
            smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appearedSignLocator)));
            elementIsVisible = true;
        } catch (Exception e) {
            //
        }

        soft.assertThat(elementIsVisible)
                .as(String.format("The string was displayed before %s seconds passed", min))
                .isFalse();

        WebDriverWait longWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(max - min));
        try {
            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appearedSignLocator)));
            elementIsVisible = true;
        } catch (Exception e) {
            //
        }

        soft.assertThat(elementIsVisible)
                .as(String.format("The string was displayed in the range between %s and %s seconds passed", min, max))
                .isTrue();
    }

    public void buttonClickFlow(int min, int max) {
        SoftAssertions soft = new SoftAssertions();
        ElementVisibilityBaseOperations.triggerElement(min,max);

        WebDriverWait buttonWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(10));
        WebElement button = buttonWait.until(ExpectedConditions.elementToBeClickable(By.id("visibility_target")));
        WebElement tooltipHeader = BaseOperations.locateElementBy("//h3[text()='Can you see me?']", "xpath");
        WebElement tooltipBody = BaseOperations.locateElementBy("//div[text()='I just removed my invisibility cloak!!']", "xpath");


        soft.assertThat(button.isDisplayed() && button.isEnabled())
                .as("Element is NOT clickable")
                .isTrue();

        button.click();

        soft.assertThat(button.isDisplayed() && button.isEnabled())
                .as("Button is GONE")
                .isTrue();

        // Adding tooltip assertions
        soft.assertThat(tooltipHeader.isDisplayed() && tooltipBody.isDisplayed())
                .as("Tooltip is NOT displayed")
                .isTrue();
        soft.assertThat(tooltipHeader.getText().equals("Can you see me?") && tooltipBody.getText().equals("I just removed my invisibility cloak!!"))
                .as("Text is incorrect. Should be: Can you see me? I just removed my invisibility cloak!! but got" + tooltipHeader.getText() + " \n" + tooltipBody.getText())
                .isTrue();

        button.click();

        soft.assertThat(tooltipHeader.isDisplayed() && tooltipBody.isDisplayed())
                .as("Tooltip is NOT displayed")
                .isFalse();
    }

}
