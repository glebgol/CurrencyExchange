package services.impl.values;

import model.Currency;

import java.util.List;

public class TestValues {
    public static List<Currency> getCurrenciesList() {
        return List.of(new Currency());
    }

    public static Currency getCurrency() {
        return new Currency();
    }
}
