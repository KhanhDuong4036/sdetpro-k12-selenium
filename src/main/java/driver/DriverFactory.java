package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    public static WebDriver getWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            this.driver = new ChromeDriver(chromeOptions);
            this.driver.manage().window().maximize();
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        }
        return this.driver;
    }

    public void closeBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }

}
