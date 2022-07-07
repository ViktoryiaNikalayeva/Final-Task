package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NexagePage extends Form {
    public static final ILink homeLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[@href = '/web/projects']"), "Home link");
    public static final String NEXAGE = "Nexage";
    public NexagePage(By locator, String name) {
        super(locator, name);
    }
    public void transitionToMainPage() {
        homeLink.clickAndWait();
    }

}
