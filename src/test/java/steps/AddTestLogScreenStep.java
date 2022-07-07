package steps;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import utils.DataSettingsUtils;
import utils.EnumUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddTestLogScreenStep {
    public static String makeScreenshot() {
        String screenshotPath = null;
        try {
            screenshotPath = String.format("%s/%s%s", DataSettingsUtils.getElementConfig("screenshots"),
                    RandomStringUtils.randomAlphanumeric(5), EnumUtils.getParam(5));
            Files.write(Paths.get(screenshotPath),
                    AqualityServices.getBrowser().getScreenshot());
        } catch (IOException e) {
            Logger.getInstance().warn("Can't create file");
        }
        return screenshotPath;
    }
    public static byte[] getContentAsArray(String screenPath) {
        byte[] content = null;
        try {
            content = Files.readAllBytes(Paths.get(screenPath));
        } catch (IOException e) {
            Logger.getInstance().debug("Failed to read all lines from the file", e);
        }
        return content;
    }
}
