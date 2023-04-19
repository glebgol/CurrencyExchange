package services.impl.factories;

import model.ExchangeRate;

import java.math.BigDecimal;

public class ExchangeRateFactory {
    public static ExchangeRate create(String from, String to) {
        if (from.equals("USD")) {
            if (to.equals("BYN")) {
                return new ExchangeRate(
                        CurrencyFactory.create("USD"),
                        CurrencyFactory.create("BYN"),
                        new BigDecimal("2.53")
                );
            } else if (to.equals("EUR")) {
                return new ExchangeRate(
                        CurrencyFactory.create("USD"),
                        CurrencyFactory.create("EUR"),
                        new BigDecimal("0.91")
                );
            }
        }
        return new ExchangeRate(null, null, null);
    }
}
