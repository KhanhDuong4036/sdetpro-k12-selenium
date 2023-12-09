package models.components.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends BaseItemComponent {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);



}
