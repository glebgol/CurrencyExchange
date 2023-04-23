package services.impl;

import dao.IExchangeRateDao;
import dto.ExchangeRateRequest;
import dto.ExchangeRateResponse;
import model.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.impl.factories.ExchangeRateFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {
    @Mock
    IExchangeRateDao exchangeRateDao;

    @InjectMocks
    ExchangeRateService service;

    @Test
    void getAllExchangeRates() {
        List<ExchangeRate> expectedRates = List.of(
                ExchangeRateFactory.create("USD", "EUR"),
                ExchangeRateFactory.create("USD", "RUB")
        );

        when(exchangeRateDao.readAll()).thenReturn(expectedRates);

        List<ExchangeRate> actualRates = service.getAllExchangeRates();

        assertEquals(expectedRates, actualRates);
    }

    @Test
    void getExchangeRateByCodes() {
        Optional<ExchangeRate> expectedExchangeRate = Optional.of(ExchangeRateFactory.create("USD", "RUB"));
        when(exchangeRateDao.read("USD", "RUB")).thenReturn(expectedExchangeRate);

        Optional<ExchangeRate> actualExchangeRate = service.getExchangeRateByCodes("USDRUB");

        assertEquals(expectedExchangeRate, actualExchangeRate);
    }

    @Test
    void getExistingExchangeRate() {
        ExchangeRateRequest exchangeRateRequest =
                new ExchangeRateRequest("USD", "BYN", new BigDecimal(100));
        when(exchangeRateDao.read("USD", "BYN"))
                .thenReturn(Optional.of(ExchangeRateFactory.create("USD", "BYN")));
        ExchangeRateResponse expectedResponse = new ExchangeRateResponse(
                ExchangeRateFactory.create("USD", "BYN"),
                new BigDecimal(100),
                new BigDecimal("253.00")
        );

        ExchangeRateResponse actualResponse = service.getExchangeRate(exchangeRateRequest).get();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getReverseExchangeRate() {
        ExchangeRateRequest exchangeRateRequest =
                new ExchangeRateRequest("BYN", "USD", new BigDecimal(100));

        when(exchangeRateDao.read("BYN", "USD"))
                .thenReturn(Optional.empty());
        when(exchangeRateDao.read("USD", "BYN"))
                .thenReturn(Optional.of(ExchangeRateFactory.create("USD", "BYN")));

        ExchangeRateResponse expectedResponse = new ExchangeRateResponse(
                ExchangeRateFactory.create("BYN", "USD"),
                new BigDecimal(100),
                new BigDecimal("40")
        );
        ExchangeRateResponse actualResponse = service.getExchangeRate(exchangeRateRequest).get();

        assertEquals(expectedResponse, actualResponse);
    }
}