package services.impl.factories;

import model.Currency;

public class CurrencyFactory {
    public static Currency create(String code) {
        return switch (code) {
            case "USD" -> new Currency("USD", "US Dollar", "$");
            case "BYN" -> new Currency("BYN", "Belarusian Ruble", "BR");
            case "EUR" -> new Currency("EUR", "Euro", "€");
            case "RUB" -> new Currency("RUB", "Russian Ruble", "₽");
            default -> throw new IllegalArgumentException("There is no currency with code: " + code);
        };
    }
}
