package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private static final By unitPriceSel = By.cssSelector(".product-unit-price");
    private static final By itemQuantitySel = By.cssSelector("input[name^='itemquantity']");
    private static final By subTotalSel = By.cssSelector(".product-subtotal");


    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice(){
        return Double.parseDouble(findElement(unitPriceSel).getText().trim());
    }

    public double itemQuantity(){
        return Double.parseDouble(findElement(itemQuantitySel).getAttribute("value").trim());
    }

    public double subTotal(){
        return Double.parseDouble(findElement(subTotalSel).getText().trim());
    }

}
