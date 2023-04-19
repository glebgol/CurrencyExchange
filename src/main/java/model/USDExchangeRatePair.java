package model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public ExchangeRate getExchangeRateFromFirstToSecond() {
        BigDecimal firstRate = firstExchangeRate.getRate();
        BigDecimal secondRate = secondExchangeRate.getRate();
        BigDecimal rate = getRate(firstRate, secondRate);
        return new ExchangeRate(firstExchangeRate.getTargetCurrency(), secondExchangeRate.getTargetCurrency(), rate);
    }

    protected BigDecimal getRate(BigDecimal firstRate, BigDecimal secondRate) {
        RoundingMode roundingMode = RoundingMode.CEILING;
        int scale = 6;
        return secondRate.divide(firstRate, scale, roundingMode);
    }
}
