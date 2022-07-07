package utils;
import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;

public class BrowserUtils {

    public static void refresh() {
        AqualityServices.getBrowser().refresh();
    }
    public static void maximize() {AqualityServices.getBrowser().maximize();}
    public static void goTo(String url) {AqualityServices.getBrowser().goTo(url);}
    public static void waitForPageToLoad() {AqualityServices.getBrowser().waitForPageToLoad();}
    public static void quit(){AqualityServices.getBrowser().quit();}
    public static void addCookie(Cookie token) {
        AqualityServices.getBrowser().getDriver().manage().addCookie(token);}
    public static void switchToTab(int index) {AqualityServices.getBrowser().tabs().switchToTab(index);}
    public static void closeTab() {AqualityServices.getBrowser().tabs().closeTab();}
}
