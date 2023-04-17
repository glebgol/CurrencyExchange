package services.impl;

import dao.IExchangeRateDao;
import model.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static services.impl.values.TestValues.getExchangeRatesList;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {
    @Mock
    IExchangeRateDao exchangeRateDao;

    @InjectMocks
    ExchangeRateService service;

    @Test
    void getAllExchangeRates() {
        List<ExchangeRate> exchangeRateList = getExchangeRatesList();
        when(exchangeRateDao.readAll()).thenReturn(exchangeRateList);

        List<ExchangeRate> exchangeRates = service.getAllExchangeRates();

        assertEquals(exchangeRateList, exchangeRates);
    }

    @Test
    void getExchangeRateByValidCodes() {
    }
}