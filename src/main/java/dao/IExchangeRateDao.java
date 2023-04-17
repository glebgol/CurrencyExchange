package dao;

import model.ExchangeRate;

import java.util.List;

public interface IExchangeRateDao {
    void create(ExchangeRate exchangeRate);
    ExchangeRate read(int id);
    ExchangeRate read(String baseCurrencyCode, String targetCurrencyCode);
    List<ExchangeRate> readAll();
    void update(ExchangeRate exchangeRate);
    void delete(ExchangeRate exchangeRate);
}
