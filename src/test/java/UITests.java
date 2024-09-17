import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITests extends BaseOperations {
    private static final String login = "admin";
    private static final String password = "admin";
    private static final String firstName = "Volodymyr";
    private static final String lastName = "Test";
    private static final String email = "uarealtek1994@gmail.com";

    @Test
    public void isCorrectText() {
        EndToEndFlows.login(login,password);

        //Check Header text
        WebElement header = BaseOperations.locateElementBy("/html/body/div[1]/div/div[1]", "xpath");
        Assert.assertEquals(header.getText(),"Dinesh's Pizza House\n" +
                "Customize your pizza by choosing from a variety of toppings, cheese and sauces.", "Incorrect text. Correct text is: " + header.getText());

        //Check Pizza properties texts
        WebElement pizzaSize = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[1]/div[1]/label", "xpath");
        WebElement pizzaFlavour = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[2]/div[1]/label","xpath");
        WebElement sauce = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[3]/div[1]/label", "xpath");
        WebElement toppings = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[4]/div[1]/label", "xpath");
        WebElement quantity = BaseOperations.locateElementBy("//*[@id=\"pizza_order_form\"]/div[5]/div[1]/label", "xpath");

        Assert.assertEquals(pizzaSize.getText(), "Pizza Size", "Nope, Should be Pizza Size");
        Assert.assertEquals(pizzaFlavour.getText(),"Pizza Flavor", "Nope, should be Pizza Flavor");
        Assert.assertEquals(sauce.getText(),"Sauce", "Nope, should be Sauce");
        Assert.assertEquals(toppings.getText(), "Toppings", "Nope, should be Toppings");
        Assert.assertEquals(quantity.getText(), "Quantity", "Nope, should be Quantity");
    }

    @Test
    public void isCorrectSetOfControlsPizzaSize() {
        EndToEndFlows.login(login,password);

        // Check count of elements
        WebElement controlsDiv = BaseOperations.locateElementBy("form-group","className");
        controlsDiv.getAttribute("childElementCount");
        Assert.assertEquals(controlsDiv.getAttribute("childElementCount"), "3", "Incorrect amount of elements. Expected - 3, but got: " + controlsDiv.getAttribute("childElementCount"));
    }

     @Test
    public void isCorrectSignUpConfirmationDataDisplayed() {
        EndToEndFlows.singUp(firstName,lastName,email,password);

        //Check correct set of elements
         WebElement elementsDiv = BaseOperations.locateElementBy("text-center","className");
         Assert.assertEquals(elementsDiv.getAttribute("childElementCount"),"3","Incorrect. Should be 3. But got: " + elementsDiv.getAttribute("childElementCount"));

         //Check favicon and title
         String title = BaseOperations.getDriver().getTitle();
         Assert.assertEquals(title,"Confirmation!","Nope! Should be 'Confirmation!' but was: " + title);

         //Check checkmark label
         WebElement checkmarkLabel = BaseOperations.locateElementBy("/html/body/div/i","xpath");
         String checkmarkColor = checkmarkLabel.getCssValue("color");
         String checkMarkFontSize = checkmarkLabel.getCssValue("font-size");
         String checkmarkPadding = checkmarkLabel.getCssValue("padding-top");

         Assert.assertEquals(checkmarkColor, "rgba(47, 172, 102, 1)", "Nope, should be rgba(47, 172, 102, 1) but got " + checkmarkLabel.getCssValue("color"));
         Assert.assertEquals(checkMarkFontSize,"45px","Nope, should be 45px but got: " + checkmarkLabel.getCssValue("font-size"));
         Assert.assertEquals(checkmarkPadding, "50px", "Nope, should be 50px, but got: " + checkmarkLabel.getCssValue("padding-top"));

         //Check confirmation header
         WebElement headerText = BaseOperations.locateElementBy("text-success","className");
         String headerTextColor = headerText.getCssValue("color");

         String headerTextSize = headerText.getCssValue("font-size");
         JavascriptExecutor js = (JavascriptExecutor) BaseOperations.getDriver();
         String script = "return window.getComputedStyle(arguments[0], ':before').getPropertyValue('font-size');";
         String headerTextSizeJs = (String) js.executeScript(script,headerText);


         Assert.assertEquals(headerText.getText(),"Confirmation", "Nope. Should be 'Confirmation' but got: " + headerText.getText());
         Assert.assertTrue(headerTextColor.equalsIgnoreCase("rgba(63, 182, 24, 1)"), "Expected rgba(63, 182, 24, 1) but was: " + headerTextColor);

         assert headerTextSizeJs != null;
         Assert.assertTrue(headerTextSizeJs.equalsIgnoreCase("37.5px"), "Expected - 45px but was: " + headerTextSize);

         //Check sub-header text
         WebElement subHeaderText = BaseOperations.locateElementBy("/html/body/div/p","xpath");
         String subHeaderFontSize = subHeaderText.getCssValue("font-size");

         Assert.assertTrue(subHeaderFontSize.equalsIgnoreCase("15px"),"Nope. FontSize should be 0.9375rem but got " + subHeaderFontSize);
         Assert.assertEquals(subHeaderText.getText(), "Form submitted successfully. It's just for testing purpose, data not saved.", "Nope, the text is wrong. Should be: " + subHeaderText.getText());
    }
}
