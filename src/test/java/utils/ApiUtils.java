package utils;
import kong.unirest.*;
import models.Response;

public class ApiUtils {
    static {
        Unirest.config().defaultBaseUrl(DataSettingsUtils.getElementConfig("urlApi"));}

    public static Response post(String request) {
        return new Response(Unirest.post(request).asString());
    }
    public static Response postAsJson(String request) {
        return new Response(Unirest.post(request).asJson());
    }
}
