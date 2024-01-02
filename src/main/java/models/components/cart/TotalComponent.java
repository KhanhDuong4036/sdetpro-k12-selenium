package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCSSSelector(".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By priceTableRowSel = By.cssSelector("table tr");
    private static final By priceTypeRowSel = By.cssSelector(".cart-total-left");
    private static final By priceValueRowSel = By.cssSelector(".cart-total-right");
    private static final By termOfServiceSel = By.cssSelector("input[id='termsofservice']");
    private static final By checkoutBtnSel = By.cssSelector("button[id='checkout']");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories() {
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowEles = findElements(priceTableRowSel);
        for (WebElement tableRowEle : priceTableRowEles) {
            WebElement priceTypeEle = tableRowEle.findElement(priceTypeRowSel);
            WebElement priceValueEle = tableRowEle.findElement(priceValueRowSel);
            String priceType = priceTypeEle.getText().replace(":", "").trim();
            Double priceValue = Double.parseDouble(priceValueEle.getText().trim());
            priceCategories.put(priceType, priceValue);
        }

        return priceCategories;
    }

    public void agreeTOS(){
        findElement(termOfServiceSel).click();
    }

    public void clickOnCheckoutBtn(){
        findElement(checkoutBtnSel).click();
    }


}
