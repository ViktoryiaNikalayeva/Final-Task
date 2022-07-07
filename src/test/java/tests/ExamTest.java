package tests;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import models.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.NexagePage;
import steps.AddNewProjectStep;
import steps.AddTestLogScreenStep;
import steps.NexageTestsDateStep;
import utils.BrowserUtils;
import utils.Request;
import utils.Utils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static pages.MainPage.RIGHT_VARIANT;
import static pages.NexagePage.NEXAGE;

public class ExamTest extends BaseTest {
    @Test
    public void examTest() {
        Response tokenResponse = Request.getTokenResponse();
        Assert.assertEquals(tokenResponse.getStatus(), HttpStatus.OK,
                String.format("Actual %s, expected %s", tokenResponse.getStatus(), HttpStatus.OK));
        String token = tokenResponse.getBody();
        Assert.assertTrue(Objects.nonNull(token) && !token.isBlank(),
                String.format("Actual token %s, Where is your token?", token));

        setup();
        MainPage mainPage = new MainPage(By.className("breadcrumb"), "Home link");
        Assert.assertTrue(mainPage.state().isDisplayed(), "Where is MainPage?");
        BrowserUtils.addCookie(new Cookie("token", token));
        BrowserUtils.refresh();
        Assert.assertEquals(MainPage.getVariant(), RIGHT_VARIANT,
                String.format("Actual %s, expected %s", MainPage.getVariant(), RIGHT_VARIANT));

        mainPage.transitionToProject(NEXAGE);
        NexagePage nexagePage = new NexagePage(By.xpath("//li[contains(., 'Nexage')]"), "Nexage sign");
        List<LocalDateTime> dateListUi = NexageTestsDateStep.getDateOfTests();
        Response testsResponse = Request.getTestsResponse();
        Assert.assertEquals(testsResponse.getStatus(), HttpStatus.OK,
                String.format("Actual %s, expected %s", testsResponse.getStatus(), HttpStatus.OK));
        List<LocalDateTime> dateListApi = NexageTestsDateStep.getAndSortListWithDateApi(testsResponse);
        Assert.assertTrue(NexageTestsDateStep.isListsAreEqual(dateListUi, dateListApi),
                "Lists are not equal");

        nexagePage.transitionToMainPage();
        AddNewProjectStep.addProject();
        Assert.assertTrue(AddNewProjectStep.checkSuccessSave(), "No save for new project");
        AddNewProjectStep.closeProjectForm();
        BrowserUtils.refresh();
        Assert.assertTrue(AddNewProjectStep.checkProjectName(), "No project in the list");
        System.out.println(Utils.getProjectName());
        mainPage.transitionToProject(Utils.getProjectName());

        Response addTestResponse = Request.getAddTestResponse();
        Assert.assertEquals(addTestResponse.getStatus(), HttpStatus.OK,
                String.format("Actual %s, expected %s", addTestResponse.getStatus(), HttpStatus.OK));
        String testId = addTestResponse.getBody();
        System.out.println(testId);
        HttpResponse<String> addLogResponse = Request.addTestLogResponse(testId);
        Assert.assertEquals(addTestResponse.getStatus(), HttpStatus.OK,
                String.format("Actual %s, expected %s", addLogResponse.getStatus(), HttpStatus.OK));
        String screenshotPath = AddTestLogScreenStep.makeScreenshot();
        HttpResponse<String> screenshotResponse = Request.addTestImgAttachmentResponse(testId, screenshotPath);
        Assert.assertEquals(screenshotResponse.getStatus(), HttpStatus.OK,
                String.format("Actual %s, expected %s", screenshotResponse.getStatus(), HttpStatus.OK));
    }
}

