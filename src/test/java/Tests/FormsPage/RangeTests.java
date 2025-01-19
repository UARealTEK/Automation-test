package Tests.FormsPage;

import Enums.URLs;
import Pages.FormsPage.FormsPage;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        log.debug(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"));
        soft.assertThat(BaseOperations.getJavaScriptPropertyValue(page.getRangeElement(),"value"))
                .isEqualTo(page.getDefaultRangeElementValue());
        log.debug(page.getDefaultRangeElementValue());
        soft.assertThat(page.isRangeLabelMatched()).isTrue();

        //Essentially - below check is included in the assertion above. Just explicitly added it separately
        soft.assertThat(page.getRangeLabel()).isEmpty();

        soft.assertAll();
    }

    //Still working on it
    @Test
    public void checkRangeSelectionUsingMouse() throws AttributeNotFoundException {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.FORMS_PAGE);
        FormsPage page = new FormsPage(getDriver());

        page.changeRange();
        soft.assertThat(page.isRangeLabelMatched()).isTrue();
        log.debug(page.getRangeLabel());
        log.debug(page.getRangeElementValue());

        soft.assertAll();
    }
}
