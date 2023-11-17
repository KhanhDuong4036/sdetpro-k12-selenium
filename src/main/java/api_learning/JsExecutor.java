package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JsExecutor {

    private static final String TARGET_URL = "https://the-internet.herokuapp.com/floating_menu";
    private static final String TO_BOTTOM_JS_SNIPPET = "window.scrollTo(0, document.body.scrollHeight);";
    private static final String TO_TOP_JS_SNIPPET = "window.scrollTo(document.body.scrollHeight, 0);";

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);

        try {
            // Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript(TO_BOTTOM_JS_SNIPPET);
            Thread.sleep(3000);

            // Scroll to top
            javascriptExecutor.executeScript(TO_TOP_JS_SNIPPET);
            Thread.sleep(3000);

            javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", driver.findElement(By.tagName("h3")));
            javascriptExecutor.executeScript("arguments[0].removeAttribute('target')", driver.findElement(By.cssSelector("[href=\"http://elementalselenium.com/\"]")));
            driver.findElement(By.cssSelector("[href=\"http://elementalselenium.com/\"]")).click();
            Thread.sleep(2000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }


}
