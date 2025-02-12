package Tests.MouseActionsTests.MouseClickActionsTests;

import Enums.ClickType;
import Enums.URLs;
import Pages.MouseActionsPage.MouseClickActions;
import Utils.BaseOperations;
import Utils.DriverOperations;
import Utils.Threshold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

@Tag("mouse_tests")
public class MouseClickActionsTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(MouseClickActionsTests.class);

    @Test
    public void checkMouseClickFormDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseClickActions page = new MouseClickActions(getDriver());

        soft.assertThat(page.isClickTypeMatched()).isTrue();
        soft.assertThat(page.getXCoordinateLabel().getText().isEmpty()).isTrue();
        soft.assertThat(page.getYCoordinateLabel().getText().isEmpty()).isTrue();

        soft.assertAll();
    }

    @RepeatedTest(4)
    public void checkMainLogic() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseClickActions page = new MouseClickActions(getDriver());
        Point randomCoordinates = page.getRandomOffset();
        ClickType type = ClickType.getRandomClickType();
        Threshold threshold = new Threshold();

        page.clickClickAreaCoordinates(randomCoordinates,type);


        soft.assertThat(threshold.isValueWithinThreshold(randomCoordinates.getX(),
                page.getExpectedClickAreaLabelValueLeft())).as(String.format("The expected value is: %s , and the actual value is: %s",page.getExpectedClickAreaLabelValueLeft(), randomCoordinates.getX()))
                .isTrue();
        soft.assertThat(threshold.isValueWithinThreshold(randomCoordinates.getY(),
                page.getExpectedClickAreaLabelValueTop())).as(String.format("The expected value is: %s , and the actual value is: %s",page.getExpectedClickAreaLabelValueTop(), randomCoordinates.getY()))
                .isTrue();

        soft.assertThat(page.isClickTypeMatched(type)).isTrue();

        soft.assertAll();
    }
}
