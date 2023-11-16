package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;

import java.time.Duration;

public class DynamicControl {

    private static final String TARGET_URL = "https://the-internet.herokuapp.com/dynamic_controls";
    private static final By CHECKBOX_FORM_SEL = By.id("checkbox-example");
    private static final By INPUT_FORM_SEL = By.id("input-example");
    private static final By CHECKBOX_INPUT_ELE_SEL = By.cssSelector("#checkbox-example input");
    private static final By INPUT_ELE_SEL = By.cssSelector("#input-example input");
    private static final By BTN_ELE_SEL = By.tagName("button");


    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        try {
            // Locate 2 parent forms
            WebElement checkboxFormEle = driver.findElement(CHECKBOX_FORM_SEL);
            WebElement inputFormEle = driver.findElement(INPUT_FORM_SEL);

            // checkbox form interaction
            WebElement checkboxInputEle = checkboxFormEle.findElement(CHECKBOX_INPUT_ELE_SEL);
            WebElement removeBtnEle = checkboxFormEle.findElement(BTN_ELE_SEL);
            System.out.println("BEFORE | is element selected: " + checkboxInputEle.isSelected());
            checkboxInputEle.click();
            System.out.println("AFTER | is element selected: " + checkboxInputEle.isSelected());

            removeBtnEle.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX_INPUT_ELE_SEL));

            // Input Form Interaction
            WebElement enabledTextFieldBtn = inputFormEle.findElement(BTN_ELE_SEL);
            enabledTextFieldBtn.click();
            wait.until(new WaitForElementEnabled(INPUT_ELE_SEL));
            WebElement textFieldEle = inputFormEle.findElement(INPUT_ELE_SEL);
            textFieldEle.sendKeys("KhanhDuong");
            Thread.sleep(2000);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
