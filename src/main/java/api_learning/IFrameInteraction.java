package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;

public class IFrameInteraction {

    private static final String TARGET_URL = "https://the-internet.herokuapp.com/iframe";
    private static final By IFRAME_SEL = By.cssSelector("iframe[id^='mce']");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            WebElement iFrameEle = driver.findElement(IFRAME_SEL);
            driver.switchTo().frame(iFrameEle);
            WebElement inputField = driver.findElement(By.id("tinymce"));
            inputField.click();
            inputField.clear();
            inputField.sendKeys("I'm Khanh Duong");
            Thread.sleep(2000);
            driver.switchTo().defaultContent();

        } catch (Exception e) {
        } finally {
            driver.quit();
        }

    }

}
