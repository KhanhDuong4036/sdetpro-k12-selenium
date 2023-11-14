package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// Select Expansion
public class SelectEx extends Select {
    private static final String OPTION_01 = "Option_1";

    public SelectEx(WebElement element) {
        super(element);
    }
    public void selectOption1(){
        selectByVisibleText(OPTION_01);
    }
}
