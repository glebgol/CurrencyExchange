package utils;

import javax.servlet.http.HttpServletRequest;

public interface ICurrencyRequestParser {
    String getCurrencyCode(HttpServletRequest request);
}
