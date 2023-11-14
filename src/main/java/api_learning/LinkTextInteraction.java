package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTextInteraction {

    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);

        try {
//            By powerByLinkTextSel = By.linkText("Elemental Selenium");
            By powerByPartialLinkTextSel = By.partialLinkText("Elemental");
//            WebElement powerByLinkTextEle = driver.findElement(powerByLinkTextSel);
            WebElement powerByPartialLinkTextEle = driver.findElement(powerByPartialLinkTextSel);
//            powerByLinkTextEle.click();
            powerByPartialLinkTextEle.click();

            //DEBUG
            try{
                Thread.sleep(2000);
            }catch (Exception ignored){

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
