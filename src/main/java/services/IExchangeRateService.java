package services;

import dto.ExchangeRateRequest;
import dto.ExchangeRateResponse;
import model.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface IExchangeRateService {
    List<ExchangeRate> getAllExchangeRates();
    Optional<ExchangeRate> getExchangeRateByCodes(String codes);
    Optional<ExchangeRateResponse> getExchangeRate(ExchangeRateRequest exchangeRateRequest);
    void addExchangeRate(ExchangeRate exchangeRate);
}
