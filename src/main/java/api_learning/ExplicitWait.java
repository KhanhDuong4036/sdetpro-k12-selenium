package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;

import java.time.Duration;

public class ExplicitWait {

    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";


    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            // ImplicitWait: Applied Globally for the whole sessions when finding element(s) | Interval time 500 milliseconds
            // ExplicitWait: Applied locally/for a specific element | Interval time 500 milliseconds
             WebDriverWait webDriverWaitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
            // webDriverWaitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("tao lao")));

            // Using customized explicit wait
            webDriverWaitDriver.until(new WaitForElementEnabled(By.cssSelector("#sth")));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
