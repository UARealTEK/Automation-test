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

// TBD: to solve the issue with Button triggering (71 row)

public class ElementsToBeVisibleTests extends BaseOperations {
    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isVisibleElement(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> isVisibleElement(7,10)),
                DynamicTest.dynamicTest("min - 2, max - 5", () -> isElementInvisible(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> isElementInvisible(7,10)));
    }

    @TestFactory
    public List<DynamicTest> exampleTestFactory2() {
        return Arrays.asList(
                DynamicTest.dynamicTest("min - 2, max - 5", () -> buttonClickFlow(2,5)),
                DynamicTest.dynamicTest("min - 7, max - 10", () -> buttonClickFlow(7,10)));
    }


    public void isVisibleElement(int min, int max) {
        SoftAssertions soft = new SoftAssertions();
        ElementVisibilityBaseOperations.triggerElementToSee(min,max);

        boolean elementIsVisible = false;
        WebElement appearedSignLocator = BaseOperations.locateElementBy("//*[@id=\"visibility_target\"]","xpath");
        WebDriverWait smallWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds((long) (min - 0.0001)));

        try {
            smallWait.until(ExpectedConditions.visibilityOf(appearedSignLocator));
            elementIsVisible = true;
        } catch (Exception e) {
            //
        }

        soft.assertThat(elementIsVisible)
                .as(String.format("The string was displayed before %s seconds passed", min))
                .isFalse();

        WebDriverWait longWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(max - min));
        try {
            longWait.until(ExpectedConditions.visibilityOf(appearedSignLocator));
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
        ElementVisibilityBaseOperations.triggerElementToSee(min,max);

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

    public void isElementInvisible(int min, int max) {
        ElementVisibilityBaseOperations.triggerElementToUnSee(min,max);
        SoftAssertions soft = new SoftAssertions();
        WebElement element = BaseOperations.locateElementBy("//*[@id=\"invisibility_target\"]","xpath");

        boolean isInVisibleElement = false;
        WebDriverWait smallWait = new WebDriverWait(BaseOperations.getDriver(),Duration.ofSeconds((long)(min - 0.001)));

        try {
            smallWait.until(ExpectedConditions.invisibilityOf(element));
            isInVisibleElement = true;
        } catch (Exception e) {
            //
        }

        soft.assertThat(isInVisibleElement)
                .as(String.format("Element became invisible before the %s seconds passed",min))
                .isFalse();

        WebDriverWait longWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(max - min));

        try {
            longWait.until(ExpectedConditions.invisibilityOf(element));
            isInVisibleElement = true;
        } catch (Exception e) {
            //
        }

        soft.assertThat(isInVisibleElement)
                .as(String.format("Element has NOT became invisible in the timeframe between %s and %s seconds",min,max))
                .isTrue();
    }

}
