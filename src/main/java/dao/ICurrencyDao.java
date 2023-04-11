package dao;

import model.Currency;

import java.util.List;

public interface ICurrencyDao {
    void create(Currency user);
    Currency read(int id);
    List<Currency> readAll();
    void update(Currency user);
    void delete(Currency userName);
}
