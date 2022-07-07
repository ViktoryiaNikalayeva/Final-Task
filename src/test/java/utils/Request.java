package utils;
import kong.unirest.ContentType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import models.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import steps.AddTestLogScreenStep;
import static pages.MainPage.RIGHT_VARIANT;

public class Request {

    public static final String PROJECT_ID = "1";
    public static final String PARAMETERS_SEPARATOR = "&";
    public static final String TOKEN_REQUEST = String.format(EnumUtils.getReq(0),
            DataSettingsUtils.getElementConfig("urlApi"),
            String.format("?variant=%s", RIGHT_VARIANT));
    public static final String TESTS_REQUEST = String.format(EnumUtils.getReq(1),
            DataSettingsUtils.getElementConfig("urlApi"),
            String.format("?projectId=%s", PROJECT_ID));
    public static final String ADD_TEST_REQUEST = String.format(EnumUtils.getReq(2),
            DataSettingsUtils.getElementConfig("urlApi"), StringUtils.joinWith(PARAMETERS_SEPARATOR,
                    String.format("?SID=%s", Utils.createSessionId()),
                    String.format("projectName=%s",EnumUtils.getParam(0)),
                    String.format("testName=%s", EnumUtils.getParam(1)),
                    String.format("methodName=%s", EnumUtils.getParam(2)),
                    String.format("env=%s", EnumUtils.getParam(3))));


    public static Response getTokenResponse() {return ApiUtils.post(TOKEN_REQUEST);}
    public static Response getTestsResponse() {return ApiUtils.postAsJson(TESTS_REQUEST);}
    public static Response getAddTestResponse() {return ApiUtils.post(ADD_TEST_REQUEST);}
    public static HttpResponse<String> addTestLogResponse(String testId) {
        return Unirest.post(String.format(EnumUtils.getReq(3), DataSettingsUtils.getElementConfig("urlApi"),
                        String.format("?testId=%s", testId)))
                .field("content", EnumUtils.getParam(4)).asString();}
    public static HttpResponse<String> addTestImgAttachmentResponse(String testId, String filePath) {
        return Unirest.post(String.format(EnumUtils.getReq(4),
                        DataSettingsUtils.getElementConfig("urlApi"),
                        String.join(PARAMETERS_SEPARATOR, String.format("?testId=%s", testId),
                                String.format("contentType=%s", ContentType.IMAGE_PNG.getMimeType()))))
                .field("content", Base64.encodeBase64String(AddTestLogScreenStep.getContentAsArray(filePath))).asString();
    }
}
