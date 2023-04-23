package services.impl.factories;

import model.ExchangeRate;

import java.math.BigDecimal;

public class ExchangeRateFactory {
    public static ExchangeRate create(String from, String to) {
        switch (from) {
            case "USD":
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
                } else if (to.equals("RUB")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("USD"),
                            CurrencyFactory.create("RUB"),
                            new BigDecimal("81.45")
                    );
                }
                break;
            case "EUR":
                if (to.equals("BYN")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("EUR"),
                            CurrencyFactory.create("BYN"),
                            new BigDecimal("2.77")
                    );
                } else if (to.equals("USD")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("EUR"),
                            CurrencyFactory.create("USD"),
                            new BigDecimal("1.1")
                    );
                } else if (to.equals("RUB")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("EUR"),
                            CurrencyFactory.create("RUB"),
                            new BigDecimal("90.38")
                    );
                }
                break;
            case "BYN":
                if (to.equals("EUR")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("BYN"),
                            CurrencyFactory.create("EUR"),
                            new BigDecimal("0.36")
                    );
                } else if (to.equals("USD")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("BYN"),
                            CurrencyFactory.create("USD"),
                            new BigDecimal("0.395256917")
                    );
                } else if (to.equals("RUB")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("BYN"),
                            CurrencyFactory.create("RUB"),
                            new BigDecimal("32.29")
                    );
                }
                break;
            case "RUB":
                if (to.equals("EUR")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("RUB"),
                            CurrencyFactory.create("EUR"),
                            new BigDecimal("0.011")
                    );
                } else if (to.equals("USD")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("RUB"),
                            CurrencyFactory.create("USD"),
                            new BigDecimal("0.012")
                    );
                } else if (to.equals("BYN")) {
                    return new ExchangeRate(
                            CurrencyFactory.create("RUB"),
                            CurrencyFactory.create("BYN"),
                            new BigDecimal("0.031")
                    );
                }
        }
        throw new IllegalArgumentException("There is no exchange rate from \"" + from + "\" to \"" + to + "\"");
    }
}
