package tests;

import models.components.FindComponent;
import models.components.FooterComponent;

public class AnnotationTests {

    public static void main(String[] args) {
        String footerComSel = new FindComponent().getComponentCssSelector(FooterComponent.class);
        System.out.println(footerComSel);
    }
}
