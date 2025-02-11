package Tests.MouseActionsTests.MouseClickActionsTests;

import Enums.ClickType;
import Enums.URLs;
import Pages.MouseActionsPage.MouseClickActions;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

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

        page.clickClickAreaCoordinates(randomCoordinates,type);

        soft.assertThat(page.getExpectedClickAreaLabelValueLeft()).isEqualTo(randomCoordinates.getX());
        soft.assertThat(page.getExpectedClickAreaLabelValueTop()).isEqualTo(randomCoordinates.getY());
        soft.assertThat(page.isClickTypeMatched(type)).isTrue();

        log.info(type.getType());
        log.info(page.getClickTypeVerify().getText());

        soft.assertAll();
    }
}
