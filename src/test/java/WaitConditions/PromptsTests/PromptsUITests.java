package WaitConditions.PromptsTests;

import SamplePages.Helper.BaseOperations;
import WaitConditions.Helper.PromptBaseOperations;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromptsUITests extends BaseOperations {

    @Test
    public void checkPromptData() {
        PromptBaseOperations.showPrompt(2,5); // Assuming minimum delay - 2 seconds, maximum - 5 seconds

        WebDriverWait alertWait = new WebDriverWait(BaseOperations.getDriver(), Duration.ofSeconds(5));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
        BaseOperations.getDriver().switchTo().alert();

        assertEquals(alert.getText(),"Choose wisely...\n" +
                "It's your life!", "Expected the message but got " + alert.getText());

        alert.accept();
    }
}
