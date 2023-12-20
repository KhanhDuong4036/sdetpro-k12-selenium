package models.components.global.header;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.ui.ExpectedConditionsEx;

@ComponentCSSSelector(value = ".header")
public class HeaderComponent extends Component {

    private static final By shoppingCartLinkSel = By.cssSelector("li#topcartlink");

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnShoppingCartLink(){
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLinkSel));
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(shoppingCartLinkSel)).click().perform();
    }
}
