package services.impl.utils;

import model.ExchangeRate;
import model.USDExchangeRatePair;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeCalculator {
    public BigDecimal convertFromBaseToTarget(BigDecimal amountToConvert, ExchangeRate exchangeRate) {
        BigDecimal rate = exchangeRate.getRate();
        return amountToConvert.multiply(rate);
    }

    public BigDecimal convertFromTargetToBase(BigDecimal amountToConvert, ExchangeRate exchangeRate) {
        BigDecimal rate = getReverseRate(exchangeRate);
        return amountToConvert.multiply(rate);
    }

    public BigDecimal convertFromUSDPair(BigDecimal amountToConvert, USDExchangeRatePair usdExchangeRatePair) {
        return null;
    }

    private BigDecimal getReverseRate(ExchangeRate exchangeRate) {
        RoundingMode roundingMode = RoundingMode.CEILING;
        int scale = 6;
        BigDecimal one = new BigDecimal(1);
        BigDecimal rate = exchangeRate.getRate();
        return one.divide(rate, scale, roundingMode);
    }
}
