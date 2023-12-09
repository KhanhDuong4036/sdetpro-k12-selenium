package models.components.global;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCSSSelector(".top-menu > li")
public class CategoryItemComponent extends Component {

    public CategoryItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement cateItemLink(){
        return component.findElement(By.tagName("a"));
    }

    public List<WebElement> sublistItems(){
        Actions actions = new Actions(driver);
        actions.moveToElement(component).perform();
        return findElements(By.cssSelector(".sublist li a"));
    }

}
