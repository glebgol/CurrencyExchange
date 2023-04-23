package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
public class ExchangeRate {
    private int id;
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;

    public ExchangeRate(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
        setBaseCurrency(baseCurrency);
        setTargetCurrency(targetCurrency);
        setRate(rate);
    }

    public ExchangeRate getReverseExchangeRate() {
        RoundingMode roundingMode = RoundingMode.CEILING;
        int scale = 6;
        BigDecimal one = new BigDecimal(1);
        return new ExchangeRate(targetCurrency, baseCurrency, one.divide(rate, scale, roundingMode));
    }
}
