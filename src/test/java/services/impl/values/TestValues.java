package services.impl.values;

import model.Currency;
import model.ExchangeRate;

import java.util.List;

public class TestValues {
    public static List<Currency> getCurrenciesList() {
        return List.of(new Currency());
    }

    public static Currency getCurrency() {
        return new Currency();
    }

    public static List<ExchangeRate> getExchangeRatesList() {
        return List.of(new ExchangeRate());
    }

    public static ExchangeRate getExchangeRate() {
        return new ExchangeRate();
    }
}
