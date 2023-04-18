package dao;

import model.ExchangeRate;
import model.USDExchangeRatePair;

import java.util.List;
import java.util.Optional;

public interface IExchangeRateDao {
    void create(ExchangeRate exchangeRate);
    Optional<ExchangeRate> read(int id);
    Optional<ExchangeRate> read(String baseCurrencyCode, String targetCurrencyCode);
    List<ExchangeRate> readAll();
    Optional<USDExchangeRatePair> readCodesWithUSDBase(String baseCurrencyCode, String targetCurrencyCode);
    void update(ExchangeRate exchangeRate);
    void delete(ExchangeRate exchangeRate);
}
