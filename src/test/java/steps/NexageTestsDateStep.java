package steps;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import com.fasterxml.jackson.core.type.TypeReference;
import models.Response;
import models.TestModel;
import org.openqa.selenium.By;
import utils.Utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NexageTestsDateStep {
    public static List<ITextBox> DATE_TESTS = AqualityServices.getElementFactory().findElements(By.xpath("//table//tbody//tr//td[4]"),
            ElementType.TEXTBOX, ElementsCount.MORE_THEN_ZERO);
    public static final Integer LIST_LENGTH = 19;
    public static final String DATE_TIME_PATTERN ="yyyy-MM-dd HH:mm:ss.S";

    public static List<LocalDateTime> getDateOfTests() {
        return getListWithDateUI(DATE_TESTS);
    }
    public static List<LocalDateTime> getListWithDateUI(List<ITextBox> list) {
        List<LocalDateTime> arrayListWithDate = new ArrayList<>();
        for (int i = 0; i <= list.size() - 1; i++) {
            LocalDateTime ldt = LocalDateTime.parse(list.get(i).getText(),
                    DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
            arrayListWithDate.add(ldt);
        }
        return arrayListWithDate;
    }
    public static List<LocalDateTime> getAndSortListWithDateApi(Response response) {
        List<LocalDateTime> arrayListDate = new ArrayList<>();
        List<TestModel> list = Utils.dsrlzRemotes(response.getBody(), new TypeReference<>() {});
        for (int i = 0; i <= list.size() - 1; i++) {
            LocalDateTime localDateTime = getDate(list.get(i).getStartTime());
            arrayListDate.add(localDateTime);
        }
        arrayListDate.sort(Collections.reverseOrder());
        return arrayListDate;
    }
    public static LocalDateTime getDate(String setDate) {
        return LocalDateTime.parse(setDate, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    public static boolean isListsAreEqual(List<LocalDateTime> setListDateUi, List<LocalDateTime> setListDateApi) {
        for (int i = 0; i <= LIST_LENGTH; i++) {
           int resultCompareTo = setListDateUi.get(i).compareTo(setListDateApi.get(i));
            if (resultCompareTo != 0) {
                return false;
            }
        }
        return true;
    }
}
