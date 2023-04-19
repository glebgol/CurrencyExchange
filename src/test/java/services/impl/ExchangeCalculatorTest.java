package services.impl;

import model.ExchangeRate;
import model.USDExchangeRatePair;
import org.junit.jupiter.api.Test;
import services.impl.utils.ExchangeCalculator;
import services.impl.factories.ExchangeRateFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeCalculatorTest {
    ExchangeCalculator exchangeCalculator = new ExchangeCalculator();

    @Test
    void convertFromBaseToTarget() {
        BigDecimal amountToConvert = new BigDecimal("123.1");
        ExchangeRate exchangeRate = ExchangeRateFactory.create("USD", "BYN");
        BigDecimal expectedConvertedAmount = new BigDecimal("311.443");

        BigDecimal actualConvertedAmount = exchangeCalculator.convertFromBaseToTarget(amountToConvert, exchangeRate);

        assertEquals(expectedConvertedAmount, actualConvertedAmount);
    }

    @Test
    void convertFromTargetToBase() {
        BigDecimal amountToConvert = new BigDecimal(100);
        ExchangeRate exchangeRate = ExchangeRateFactory.create("USD", "BYN");
        BigDecimal expectedConvertedAmount = new BigDecimal("39.525700");

        BigDecimal actualConvertedAmount = exchangeCalculator.convertFromTargetToBase(amountToConvert, exchangeRate);

        assertEquals(expectedConvertedAmount, actualConvertedAmount);
    }

    @Test
    void convertFromUSDPair() {
        BigDecimal amountToConvert = new BigDecimal(100);
        USDExchangeRatePair usdExchangeRatePair = new USDExchangeRatePair(
                ExchangeRateFactory.create("USD", "BYN"),
                ExchangeRateFactory.create("USD", "EUR")
        );
        BigDecimal expectedConvertedAmount = new BigDecimal("35.968400");

        BigDecimal actualConvertedAmount = exchangeCalculator.convertFromUSDPair(amountToConvert, usdExchangeRatePair);

        assertEquals(expectedConvertedAmount, actualConvertedAmount);
    }
}