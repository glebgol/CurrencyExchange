package services.impl;

import model.ExchangeRate;
import org.junit.jupiter.api.Test;
import services.impl.utils.ExchangeCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeCalculatorTest {
    ExchangeCalculator exchangeCalculator = new ExchangeCalculator();

    @Test
    void convertFromBaseToTarget() {
        BigDecimal amountToConvert = new BigDecimal("123.1");
        BigDecimal rate = new BigDecimal("2.3");
        ExchangeRate exchangeRate = new ExchangeRate(null, null, rate);
        BigDecimal expectedConvertedAmount = new BigDecimal("283.13");

        BigDecimal actualConvertedAmount = exchangeCalculator.convertFromBaseToTarget(amountToConvert, exchangeRate);

        assertEquals(expectedConvertedAmount, actualConvertedAmount);
    }

    @Test
    void convertFromTargetToBase() {
        BigDecimal amountToConvert = new BigDecimal(100);
        BigDecimal rate = new BigDecimal("2.3");
        ExchangeRate exchangeRate = new ExchangeRate(null, null, rate);
        BigDecimal expectedConvertedAmount = new BigDecimal("43.478260");

        BigDecimal actualConvertedAmount = exchangeCalculator.convertFromBaseToTarget(amountToConvert, exchangeRate);

        assertEquals(expectedConvertedAmount, actualConvertedAmount);
    }

    @Test
    void convertFromUSDPair() {
    }
}