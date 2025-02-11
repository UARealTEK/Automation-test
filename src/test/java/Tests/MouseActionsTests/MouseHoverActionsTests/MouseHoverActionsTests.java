package Tests.MouseActionsTests.MouseHoverActionsTests;

import Enums.URLs;
import Pages.MouseActionsPage.MouseHoverActions;
import Utils.BaseOperations;
import Utils.DriverOperations;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MouseHoverActionsTests extends DriverOperations {

    @Test
    public void checkDefaultState() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseHoverActions page = new MouseHoverActions(getDriver());

        soft.assertThat(page.isAllElementsDisplayed()).isFalse();
        soft.assertThat(page.getOptionValidate().getText()).isEmpty();

        page.hoverOnButton();
        soft.assertThat(page.isAllElementsDisplayed()).isTrue();

        page.hoverOffButton();
        soft.assertThat(page.isAllElementsDisplayed()).isFalse();

        soft.assertAll();
    }

    @Test
    public void checkOptionSelection() {
        SoftAssertions soft = new SoftAssertions();
        BaseOperations.navigateTo(URLs.MOUSE_EVENTS);
        MouseHoverActions page = new MouseHoverActions(getDriver());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<WebElement> options = page.getOptions();

        while (!options.isEmpty()) {
            WebElement randomOption = options.get(random.nextInt(options.size()));
            page.hoverOnButton();

            soft.assertThat(page.isAllElementsDisplayed()).isTrue();

            page.selectRandomOption(randomOption);

            soft.assertThat(page.isOptionAndLabelMatched(randomOption)).isTrue();

            page.hoverOffButton();

            options.remove(randomOption);
        }

        soft.assertAll();
    }
}
