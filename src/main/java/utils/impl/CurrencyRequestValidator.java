package utils.impl;

import utils.ICurrencyRequestValidator;

public class CurrencyRequestValidator implements ICurrencyRequestValidator {
    @Override
    public boolean isValidCurrencyCode(String code) {
        return code != null && code.length() == 3;
    }
}
