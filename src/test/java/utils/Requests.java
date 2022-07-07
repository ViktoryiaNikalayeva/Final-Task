package utils;

public enum Requests {
    GET_TOKEN("http://%s/token/get%s"),
    GET_TESTS_JSON("http://%s/test/get/json%s"),
    ADD_TEST("http://%s/test/put%s"),
    ADD_TEST_LOG("http://%s/test/put/log%s"),
    ADD_TEST_ATTACHMENT("http://%s/test/put/attachment%s");

    private final String req;

    Requests(String req) {this.req = req;}
    public String getReq() {return req;}
}
