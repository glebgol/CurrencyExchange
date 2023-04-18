package services;

import dto.ExchangeRateResponse;
import model.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IExchangeRateService {
    List<ExchangeRate> getAllExchangeRates();
    Optional<ExchangeRate> getExchangeRateByCodes(String codes);
    ExchangeRateResponse getExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount);
}
