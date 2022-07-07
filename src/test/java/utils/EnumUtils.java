package utils;
import java.util.List;

public class EnumUtils {
    private static final List<Parameters> PARAM_VALUES = List.of(Parameters.values());
    private static final List<Requests> REQUEST_VALUES = List.of(Requests.values());

    public static Parameters getParamValue(int index) {
        return PARAM_VALUES.get(index);
    }
    public static Requests getRequestValue(int index) {
        return REQUEST_VALUES.get(index);
    }

    public static String getParam(int index) {return getParamValue(index).getParam();}
    public static String getReq(int index) {
        return getRequestValue(index).getReq();
    }
}
