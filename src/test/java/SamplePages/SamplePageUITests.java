package SamplePages;

import SamplePages.Helper.BaseOperations;
import SamplePages.Helper.EndToEndFlows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SamplePageUITests extends BaseOperations {
    private static final String login = "admin";
    private static final String password = "admin";
    private static final String firstName = "Volodymyr";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";

    @Test
    public void isCorrectText() {
        SoftAssertions soft = new SoftAssertions();
        EndToEndFlows.login(login,password);

        //Check Header text
        WebElement header = BaseOperations.locateElementBy("/html/body/div[1]/div/div[1]", "xpath");
        soft.assertThat(header.getText().equals("Dinesh's Pizza House\n" +
                        "Customize your pizza by choosing from a variety of toppings, cheese and sauces."))
                        .as("Incorrect text. Correct text is: Dinesh's Pizza House\\n\" +\n" + "\"Customize your pizza by choosing from a variety of toppings, cheese and sauces.")
                        .isTrue();

        //Check Pizza properties texts
        WebElement pizzaSize = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[1]/div[1]/label", "xpath");
        WebElement pizzaFlavour = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[2]/div[1]/label","xpath");
        WebElement sauce = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[3]/div[1]/label", "xpath");
        WebElement toppings = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[4]/div[1]/label", "xpath");
        WebElement quantity = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[5]/div[1]/label", "xpath");

        soft.assertThat(pizzaSize.getText().equals("Pizza Size"))
                .as("Nope, Should be Pizza Size")
                .isTrue();

        soft.assertThat(pizzaFlavour.getText().equals("Pizza Flavour"))
                .as("Nope, should be Pizza Flavor")
                .isTrue();

        soft.assertThat(sauce.getText().equals("Sauce"))
                .as("Nope, should be Sauce")
                .isTrue();

        soft.assertThat(toppings.getText().equals("Toppings"))
                .as("Nope, should be Toppings")
                .isTrue();

        soft.assertThat(quantity.getText().equals("Quantity"))
                .as("Nope, should be Quantity")
                .isTrue();
    }

    @Test
    public void isCorrectSetOfControlsPizzaSize() {
        EndToEndFlows.login(login,password);

        // Check count of elements
        WebElement controlsDiv = BaseOperations.locateElementBy("form-group","className");
        controlsDiv.getAttribute("childElementCount");
        assertEquals(controlsDiv.getAttribute("childElementCount"), "3", "Incorrect amount of elements. Expected - 3, but got: " + controlsDiv.getAttribute("childElementCount"));
    }

     @Test
    public void isCorrectSignUpConfirmationDataDisplayed() {
        SoftAssertions soft = new SoftAssertions();
        EndToEndFlows.singUp(firstName,lastName,email,password);

        //Check correct set of elements
         WebElement elementsDiv = BaseOperations.locateElementBy("text-center","className");
         soft.assertThat(elementsDiv.getAttribute("childElementCount").equals("3"))
                 .as("Incorrect. Should be 3. But got: \" + elementsDiv.getAttribute(\"childElementCount")
                 .isTrue();

         //Check favicon and title
         String title = BaseOperations.getDriver().getTitle();
         assert title != null;
         soft.assertThat(title.equals("Confirmation!"))
                 .as("Nope! Should be 'Confirmation!' but was: " + title)
                 .isTrue();

         //Check checkmark label
         WebElement checkmarkLabel = BaseOperations.locateElementBy("/html/body/div/i","xpath");
         String checkmarkColor = checkmarkLabel.getCssValue("color");
         String checkMarkFontSize = checkmarkLabel.getCssValue("font-size");
         String checkmarkPadding = checkmarkLabel.getCssValue("padding-top");

         soft.assertThat(checkmarkColor.equals("rgba(47, 172, 102, 1)"))
                 .as("Nope, should be rgba(47, 172, 102, 1) but got " + checkmarkLabel.getCssValue("color"))
                 .isTrue();

         soft.assertThat(checkMarkFontSize.equals("45px"))
                 .as("Nope, should be 45px but got: " + checkmarkLabel.getCssValue("font-size"))
                 .isTrue();

         soft.assertThat(checkmarkPadding.equals("50px"))
                 .as("Nope, should be 50px, but got: " + checkmarkLabel.getCssValue("padding-top"))
                 .isTrue();

         //Check confirmation header
         WebElement headerText = BaseOperations.locateElementBy("text-success","className");
         String headerTextColor = headerText.getCssValue("color");

         String headerTextSize = headerText.getCssValue("font-size");
         JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
         String script = "return window.getComputedStyle(arguments[0], ':before').getPropertyValue('font-size');";
         String headerTextSizeJs = (String) js.executeScript(script,headerText);

         soft.assertThat(headerText.getText().equals("Confirmation"))
                 .as("Nope. Should be 'Confirmation' but got: " + headerText.getText())
                 .isTrue();

         soft.assertThat(headerTextColor.equalsIgnoreCase("rgba(63, 182, 24, 1)"))
                 .as("Expected rgba(63, 182, 24, 1) but was: " + headerTextColor)
                 .isTrue();

         assert headerTextSizeJs != null;
         soft.assertThat(headerTextSizeJs.equalsIgnoreCase("37.5px"))
                 .as("Expected - 45px but was: " + headerTextSize)
                 .isTrue();

         //Check sub-header text
         WebElement subHeaderText = BaseOperations.locateElementBy("/html/body/div/p","xpath");
         String subHeaderFontSize = subHeaderText.getCssValue("font-size");

         soft.assertThat(subHeaderFontSize.equalsIgnoreCase("15px"))
                 .as("Nope. FontSize should be 0.9375rem but got " + subHeaderFontSize)
                 .isTrue();

         soft.assertThat(subHeaderText.getText().equals("Form submitted successfully. It's just for testing purpose, data not saved."))
                 .as("Nope, the text is wrong. Should be: " + subHeaderText.getText())
                 .isTrue();
    }
}
