package services.impl;

import dao.ICurrencyDao;
import model.Currency;
import services.ICurrencyService;

import java.util.List;
import java.util.Optional;

public class CurrencyService implements ICurrencyService {
    private final ICurrencyDao currencyDao;
    public CurrencyService(ICurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDao.readAll();
    }

    @Override
    public Optional<Currency> getCurrencyByCode(String code) {
        return currencyDao.read(code);
    }
}
