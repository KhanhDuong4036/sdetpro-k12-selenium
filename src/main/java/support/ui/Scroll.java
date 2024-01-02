package support.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scroll {
    private static final String TO_BOTTOM_JS_SNIPPET = "window.scrollTo(0, document.body.scrollHeight);";
    private static final String TO_TOP_JS_SNIPPET = "window.scrollTo(document.body.scrollHeight, 0);";


    public static void toTop(WebDriver driver){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(TO_BOTTOM_JS_SNIPPET);
    }

    public static void toBottom(WebDriver driver){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(TO_TOP_JS_SNIPPET);

    }

}
