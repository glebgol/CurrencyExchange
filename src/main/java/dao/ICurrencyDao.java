package dao;

import model.Currency;

import java.util.List;
import java.util.Optional;

public interface ICurrencyDao {
    void create(Currency currency);
    Optional<Currency> read(int id);
    Optional<Currency> read(String code);
    List<Currency> readAll();
    void update(Currency currency);
    void delete(Currency currency);
}
