package services;

import model.Currency;

import java.util.List;
import java.util.Optional;

public interface ICurrencyService {
    List<Currency> getAllCurrencies();
    Optional<Currency> getCurrencyByCode(String code);
}
