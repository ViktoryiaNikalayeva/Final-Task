package tests;
import org.testng.annotations.AfterMethod;
import utils.BrowserUtils;
import utils.DataSettingsUtils;

public class BaseTest {
    public void setup() {
        BrowserUtils.maximize();
        BrowserUtils.goTo(DataSettingsUtils.getElementCreds("urlWeb"));
        BrowserUtils.waitForPageToLoad();
    }
    @AfterMethod
    public void tearDown() {
        BrowserUtils.quit();
    }
}
