package services;

import model.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface IExchangeRateService {
    List<ExchangeRate> getAllExchangeRates();
    Optional<ExchangeRate> getExchangeRateByCodes(String codes);
}
