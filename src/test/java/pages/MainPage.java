package pages;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    public static final ITextBox NUMBER_OF_VARIANT = AqualityServices.getElementFactory().getTextBox(By.xpath("//span[contains(text(), 'Version')]"), "Variant text");
    public static final String RIGHT_VARIANT = "2";
    public static final String REGEX = "\\D+";
    public static final String PROJECT_LINK = "//a[@class = 'list-group-item' and contains(., '%s')]";
    public MainPage(By locator, String name) {
        super(locator, name);
    }

    public static String getVariant() {
        return NUMBER_OF_VARIANT.getText().replaceAll(REGEX, "");
    }
    public void transitionToProject(String projectName) {
        AqualityServices.getElementFactory().getLink
                (By.xpath(String.format(PROJECT_LINK, projectName)), "Project link").clickAndWait();
    }
}
