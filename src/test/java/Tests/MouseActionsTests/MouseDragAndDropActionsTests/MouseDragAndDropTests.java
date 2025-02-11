package Tests.MouseActionsTests.MouseDragAndDropActionsTests;

import Enums.URLs;
import Pages.MouseActionsPage.MouseDragAndDropActions;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class MouseDragAndDropTests extends DriverOperations {

    private static final Logger log = LogManager.getLogger(MouseDragAndDropTests.class);

    @Test
    public void checkDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseDragAndDropActions page = new MouseDragAndDropActions(getDriver());

        soft.assertThat(page.getDragValidate().getText()).isEqualTo(page.getExpectedDefaultDragTargetText());
        log.info(page.getDragValidate());

        soft.assertAll();
    }

    @Test
    public void checkMainLogic() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseDragAndDropActions page = new MouseDragAndDropActions(getDriver());

        page.dropItem();

        soft.assertThat(page.getDragValidate().getText()).isEqualTo(page.getExpectedValidDragTargetText());
        soft.assertThat(page.isDragAndDropSuccessful()).isTrue();

        soft.assertAll();
    }
}
