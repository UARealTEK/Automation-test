package Tests.MouseActionsTests;

import Enums.URLs;
import Pages.MouseActionsPage.MouseClickActions;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class MouseClickActionsTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(MouseClickActionsTests.class);

    @Test
    public void checkMouseClickFormDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseClickActions page = new MouseClickActions(getDriver());

        soft.assertThat(page.getClickTypeVerify().getText().isEmpty()).isTrue();
        soft.assertThat(page.getXCoordinateLabel().getText().isEmpty()).isTrue();
        soft.assertThat(page.getYCoordinateLabel().getText().isEmpty()).isTrue();

        soft.assertAll();
    }

    @Test
    public void checkMainLogic() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseClickActions page = new MouseClickActions(getDriver());

        log.info(page.getClickAreaLocation());
        log.info(page.getClickAreaDimension());

        soft.assertAll();
    }
}
