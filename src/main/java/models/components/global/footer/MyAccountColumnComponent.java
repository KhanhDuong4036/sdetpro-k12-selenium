package models.components.global.footer;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".column.my-account")
public class MyAccountColumnComponent extends FooterColumnComponent{

    public MyAccountColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
