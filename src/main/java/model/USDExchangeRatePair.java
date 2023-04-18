package model;

import lombok.Getter;

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
}
