package models.pages;

import models.components.product.ProductItemComponent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePage extends BasePage{

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<ProductItemComponent> productItemComponentList(){
        return findComponents(ProductItemComponent.class);
    }


}
