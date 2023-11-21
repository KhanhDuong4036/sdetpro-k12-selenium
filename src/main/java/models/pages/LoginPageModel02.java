package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageModel02 {

    private final WebDriver driver;
    private static final By USERNAME_SEL = By.id("");
    private static final By PASSWORD_SEL = By.id("");
    private static final By LOGIN_BTN_SEL = By.id("");


    public LoginPageModel02(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsername(String usernameStr) {
        this.driver.findElement(USERNAME_SEL).sendKeys(usernameStr);
    }

    public void inputPassword(String passwordStr) {
        this.driver.findElement(PASSWORD_SEL).sendKeys(passwordStr);
    }

    public void clickLoginBtn() {
        this.driver.findElement(LOGIN_BTN_SEL).click();
    }
}
