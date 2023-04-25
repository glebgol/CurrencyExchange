package utils.impl;

import utils.ICurrencyRequestParser;

import javax.servlet.http.HttpServletRequest;

public class CurrencyRequestParser implements ICurrencyRequestParser {
    @Override
    public String getCurrencyCode(HttpServletRequest request) {
        String path = request.getPathInfo();
        return path.replaceFirst("/", "").toUpperCase();
    }
}
