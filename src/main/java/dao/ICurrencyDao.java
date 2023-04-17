package dao;

import model.Currency;

import java.util.List;

public interface ICurrencyDao {
    void create(Currency currency);
    Currency read(int id);
    List<Currency> readAll();
    void update(Currency currency);
    void delete(Currency currency);
}
