package dao;

import model.ExchangeRate;

import java.util.List;

public interface IExchangeRateDao {
    void create(ExchangeRate exchangeRate);
    ExchangeRate read(int id);
    List<ExchangeRate> readAll();
    void update(ExchangeRate exchangeRate);
    void delete(ExchangeRate exchangeRate);
}
