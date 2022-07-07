package utils;

public enum Parameters {
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENVIRONMENT("env"),
    LOG("log"),
    PNG(".png");
    private final String param;

    Parameters(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}