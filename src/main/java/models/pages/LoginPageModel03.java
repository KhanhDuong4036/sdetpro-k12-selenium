package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageModel03 {

    private final WebDriver driver;
    private static final By USERNAME_SEL = By.id("");
    private static final By PASSWORD_SEL = By.id("");
    private static final By LOGIN_BTN_SEL = By.id("");


    public LoginPageModel03(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageModel03 inputUsername(String usernameStr) {
        this.driver.findElement(USERNAME_SEL).sendKeys(usernameStr);
        return this;
    }

    public LoginPageModel03 inputPassword(String passwordStr) {
        this.driver.findElement(PASSWORD_SEL).sendKeys(passwordStr);
        return this;
    }

    public void clickLoginBtn() {
        this.driver.findElement(LOGIN_BTN_SEL).click();
    }
}
