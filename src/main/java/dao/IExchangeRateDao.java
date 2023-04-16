package dao;

import model.ExchangeRate;

import java.util.List;

public interface IExchangeRateDao {
    void create(ExchangeRate user);
    ExchangeRate read(int id);
    List<ExchangeRate> readAll();
    void update(ExchangeRate user);
    void delete(ExchangeRate userName);
}
