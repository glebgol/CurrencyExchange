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
        List<Currency> expectedCurrencies = getCurrenciesList();
        when(currencyDao.readAll()).thenReturn(expectedCurrencies);

        List<Currency> actualCurrencies = service.getAllCurrencies();

        assertEquals(expectedCurrencies, actualCurrencies);
    }

    @Test
    void getCurrencyByCode() {
        Optional<Currency> expectedCurrency = Optional.of(getCurrency());
        when(currencyDao.read(anyString())).thenReturn(expectedCurrency);

        Optional<Currency> actualCurrency = service.getCurrencyByCode("USD");

        assertEquals(expectedCurrency, actualCurrency);
    }
}