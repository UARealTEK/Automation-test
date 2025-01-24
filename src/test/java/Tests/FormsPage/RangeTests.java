package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.Range.RangeField;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.management.AttributeNotFoundException;
import java.util.concurrent.ThreadLocalRandom;


public class RangeTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(RangeTests.class);

    @Test
    public void checkRangeDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        RangeField page = new RangeField(getDriver());

        //Aria role is slider, but type of the element is 'range' (by default HTML markup)
        soft.assertThat(page.getRangeElement().getAriaRole())
                .isEqualTo("slider");
        log.info(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"));
        soft.assertThat(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"))
                .isEqualTo(page.getDefaultRangeElementValue());
        log.info(page.getDefaultRangeElementValue());

        soft.assertThat(page.getRangeTitle().getAttribute("for")).isEqualTo(page.getRangeElement().getAttribute("id"));
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        //Essentially - below check is included in the assertion above. Just explicitly added it separately
        soft.assertThat(page.getRangeLabel()).isEmpty();

        soft.assertAll();
    }

    @RepeatedTest(5)
    public void checkRangeSelectionUsingMouse() throws AttributeNotFoundException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        RangeField page = new RangeField(getDriver());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int optionToSelect = random.nextInt(page.getRangeElementMaxSize() + 1);

        log.info("Before action - label: {}", page.getRangeLabel());
        page.changeRangeViaMouseInteraction(optionToSelect);
        log.info("The max range is: {}", page.getRangeElementMaxSize());
        log.info("After action - label: {}", page.getRangeLabel());
        log.info("After action - 'value' attribute: {}", page.getRangeElementValue());
        soft.assertThat(optionToSelect).isEqualTo(Integer.parseInt(page.getRangeElementValue()));
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        soft.assertAll();
    }

    @RepeatedTest(5)
    public void checkRangeSelectionUsingKeyboard() throws AttributeNotFoundException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        RangeField page = new RangeField(getDriver());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int optionToSelect = random.nextInt(page.getRangeElementMaxSize() + 1);

        page.changeRangeViaKeyboard(optionToSelect);
        log.debug("current value preferred by the user is: {}", optionToSelect);
        log.debug("value which was selected: {}", page.getRangeElementValue());
        soft.assertThat(optionToSelect).isEqualTo(Integer.parseInt(page.getRangeElementValue()));
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        page.setRangeToMaxOption();
        log.debug("I should see here number 5 ->> {}", page.getRangeElementValue());
        soft.assertThat(Integer.parseInt(page.getRangeElementValue())).isEqualTo(page.getRangeElementMaxSize());
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        page.setRangeToMinOption();
        log.debug("I should see here number 0 ->> {}", page.getRangeElementValue());
        soft.assertThat(Integer.parseInt(page.getRangeElementValue())).isEqualTo(page.getRangeElementMinSize());
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        soft.assertThat(page.isRangeLabelMatched()).isTrue();
        soft.assertAll();
    }

    @Test
    public void checkRangeDragging() throws AttributeNotFoundException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        RangeField page = new RangeField(getDriver());

        int remainingOptions = page.getRangeElementMaxSize() + 1;
        int currentOffset = 0;

        while (remainingOptions != 0) {
            page.dragRangeByOffset(currentOffset);
            currentOffset += page.getRangeOptionWidth();
            remainingOptions--;
            log.debug("current option is: {}", page.getRangeElementValue());
            log.debug("current label value is: {}", page.getRangeLabel());
            soft.assertThat(page.isRangeLabelMatched()).isTrue();
        }

        soft.assertAll();
    }
}
