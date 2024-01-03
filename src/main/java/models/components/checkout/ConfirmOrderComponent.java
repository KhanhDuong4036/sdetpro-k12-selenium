package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCSSSelector("#opc-confirm_order")
public class ConfirmOrderComponent extends Component {

    private static final By confirmBtnSel = By.cssSelector("input[class*='confirm-order-next-step-button']");

    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);

    }

    public void clickOnConfirmOrderBtn() {
        findElement(confirmBtnSel).click();
        wait.until(ExpectedConditions.urlContains("/checkout/completed/"));
    }


}
