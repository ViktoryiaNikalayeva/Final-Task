package steps;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;
import utils.BrowserUtils;
import utils.Utils;

public class AddNewProjectStep {

    public static final IButton ADD_BUTTON = AqualityServices.getElementFactory().getButton(By.xpath("//a[@href='addProject']"), "Add button");
    public static final ITextBox PROJECT_INPUT = AqualityServices.getElementFactory().getTextBox(By.id("projectName"), "Project input");
    public static final IButton SAVE_BUTTON = AqualityServices.getElementFactory().getButton(By.xpath("//button[@type = 'submit']"), "Save button");
    public static final String SAVE_SIGN = "//div[@class='alert alert-success' and contains (., '%s')]";
    public static final String PROJECT_NAME ="//a[contains(@class,'list-group-item') and contains(., '%s')]";

    public static void addProject() {
        ADD_BUTTON.clickAndWait();
        BrowserUtils.switchToTab(1);
        PROJECT_INPUT.clearAndType(Utils.createProjectName());
        SAVE_BUTTON.clickAndWait();
    }
    public static boolean checkProjectName() {
        return AqualityServices.getElementFactory().getLink(By.xpath(String.format(PROJECT_NAME, Utils.getProjectName())),
                "Project Name").state().isDisplayed();
    }
    public static boolean checkSuccessSave() {
        return AqualityServices.getElementFactory().getLabel(By.xpath(String.format(SAVE_SIGN, Utils.getProjectName())),
                "Save sign").state().isDisplayed();
    }
    public static void closeProjectForm() {
        BrowserUtils.closeTab();
        BrowserUtils.switchToTab(0);
    }
}
