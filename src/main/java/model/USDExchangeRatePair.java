package model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class USDExchangeRatePair {
    private ExchangeRate firstExchangeRate;
    private ExchangeRate secondExchangeRate;

    public void setFirstExchangeRate(ExchangeRate firstExchangeRate) {
        if (isNotUSDExchangeRate(firstExchangeRate)) {
            throw new IllegalArgumentException("base currency should be USD!");
        }
        this.firstExchangeRate = firstExchangeRate;
    }

    public void setSecondExchangeRate(ExchangeRate secondExchangeRate) {
        if (isNotUSDExchangeRate(secondExchangeRate)) {
            throw new IllegalArgumentException("base currency should be USD!");
        }
        this.secondExchangeRate = secondExchangeRate;
    }

    public USDExchangeRatePair(ExchangeRate firstExchangeRate, ExchangeRate secondExchangeRate) {
        setFirstExchangeRate(firstExchangeRate);
        setSecondExchangeRate(secondExchangeRate);
    }

    private boolean isNotUSDExchangeRate(ExchangeRate exchangeRate) {
        return !exchangeRate.getBaseCurrency().getCode().equals("USD");
    }

    public BigDecimal getExchangeRateFromFirstToSecond() {
        BigDecimal firstRate = firstExchangeRate.getRate();
        BigDecimal secondRate = secondExchangeRate.getRate();

        RoundingMode roundingMode = RoundingMode.CEILING;
        int scale = 6;

        return firstRate.divide(secondRate, scale, roundingMode) ;
    }
}
