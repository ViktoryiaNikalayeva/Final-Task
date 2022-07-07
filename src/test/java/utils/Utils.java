package utils;
import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static <T> List<T> dsrlzRemotes(String body, TypeReference<List<T>> genericType) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(body, genericType);
        } catch (IOException e) {
            AqualityServices.getLogger().debug("Deserialization is failed");
        }
        return null;
    }
    private static String randomProjectName;
    public static String createProjectName() {
        return randomProjectName = RandomStringUtils.random(10, DataSettingsUtils.getElementConfig("characters"));
    }
    public static String getProjectName() {
        return randomProjectName;
    }

    public static String createSessionId() {
        return  RandomStringUtils.randomNumeric(5);
    }
}
