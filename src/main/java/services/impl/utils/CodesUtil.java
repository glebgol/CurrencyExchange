package services.impl.utils;

public class CodesUtil {
    public static String getBaseCurrencyCode(String codes) {
        return codes.substring(0, 3);
    }

    public static String getTargetCurrencyCode(String codes) {
        return codes.substring(3, 6);
    }
}
