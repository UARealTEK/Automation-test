package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


public class RangeTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(RangeTests.class);

    @Test
    public void checkRangeDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        //Aria role is slider, but type of the element is 'range' (by default HTML markup)
        soft.assertThat(page.getRangeElement().getAriaRole())
                .isEqualTo("slider");
        log.info(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"));
        soft.assertThat(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"))
                .isEqualTo(page.getDefaultRangeElementValue());
        log.info(page.getDefaultRangeElementValue());
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        //Essentially - below check is included in the assertion above. Just explicitly added it separately
        soft.assertThat(page.getRangeLabel()).isEmpty();

        soft.assertAll();
    }

    @RepeatedTest(5)
    public void checkRangeSelectionUsingMouse() throws AttributeNotFoundException, InterruptedException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int selectedOption = random.nextInt(1,page.getRangeElementMaxSize() + 1);

        log.info("Before action - label: {}", page.getRangeLabel());
        System.out.flush(); // Ensure logs are immediately displayed
        page.changeRangeViaMouseInteraction(selectedOption);
        log.info("The max range is: {}", page.getRangeElementMaxSize());
        System.out.flush(); // Ensure logs are immediately displayed
        log.info("After action - label: {}", page.getRangeLabel());
        System.out.flush(); // Ensure logs are immediately displayed
        log.info("After action - 'value' attribute: {}", page.getRangeElementValue());

        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkRangeSelectionUsingKeyboard() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        log.info("Before action - label text: {}", page.getRangeLabel());
        page.changeRangeViaKeyboard();
        log.info("After action - label text: {}", page.getRangeLabel());

        soft.assertAll();
    }
}
