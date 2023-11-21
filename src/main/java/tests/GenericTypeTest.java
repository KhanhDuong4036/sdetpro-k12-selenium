package tests;

import models.components.ComponentExploring;
import models.components.ExternalLoginPage;
import models.components.InternalLoginPage;

public class GenericTypeTest {

    public static void main(String[] args) {
        ComponentExploring componentExploring = new ComponentExploring();

        componentExploring.login(InternalLoginPage.class, "Internal User");
        componentExploring.login(ExternalLoginPage.class, "External User");
    }
}
