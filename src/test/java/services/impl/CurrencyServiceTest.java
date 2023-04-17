package services.impl;

import dao.ICurrencyDao;
import model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static services.impl.values.TestValues.getCurrenciesList;
import static services.impl.values.TestValues.getCurrency;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
    @InjectMocks
    CurrencyService service;
    @Mock
    ICurrencyDao currencyDao;

    @Test
    void getAllCurrencies() {
        List<Currency> currencyList = getCurrenciesList();
        when(currencyDao.readAll()).thenReturn(currencyList);

        List<Currency> currencies = service.getAllCurrencies();

        assertEquals(currencyList, currencies);
    }

    @Test
    void getCurrencyByCode() {
        Currency currency = getCurrency();
        when(currencyDao.read(anyString())).thenReturn(currency);

        Optional<Currency> optionalCurrency = service.getCurrencyByCode("USD");

        assertEquals(currency, optionalCurrency.get());
    }
}