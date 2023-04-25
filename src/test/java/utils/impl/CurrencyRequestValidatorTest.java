package utils.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ICurrencyRequestValidator;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRequestValidatorTest {
    ICurrencyRequestValidator currencyRequestValidator;

    @BeforeEach
    void setUp() {
        currencyRequestValidator = new CurrencyRequestValidator();
    }

    @Test
    void isValidCurrencyCode_notValidCode_returnsFalse() {
        String notValidCode = "*";

        boolean isValid = currencyRequestValidator.isValidCurrencyCode(notValidCode);

        assertFalse(isValid);
    }

    @Test
    void isValidCurrencyCode_validCode_returnsTrue() {
        String validCode = "USD";

        boolean isValid = currencyRequestValidator.isValidCurrencyCode(validCode);

        assertTrue(isValid);
    }
}