package models.components.global.footer;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(value = ".footer")
public class FooterComponent extends Component {
    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public InformationColumnComponent informationColumnComponent(){
        return findComponent(InformationColumnComponent.class);
    }

    public CustomerServiceColumnComponent customerServiceColumnComponent(){
        return findComponent(CustomerServiceColumnComponent.class);
    }

    public MyAccountColumnComponent myAccountColumnComponent(){
        return findComponent(MyAccountColumnComponent.class);
    }
    public FollowUsColumnComponent followUsColumnComponent(){
        return findComponent(FollowUsColumnComponent.class);
    }
}
