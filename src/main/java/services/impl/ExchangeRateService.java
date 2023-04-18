package services.impl;

import dao.IExchangeRateDao;
import dto.ExchangeRateResponse;
import model.ExchangeRate;
import services.IExchangeRateService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static services.impl.utils.CodesUtil.*;

public class ExchangeRateService implements IExchangeRateService {
    private final IExchangeRateDao exchangeRateDao;
    public ExchangeRateService(IExchangeRateDao exchangeRateDao) {
        this.exchangeRateDao = exchangeRateDao;
    }

    @Override
    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateDao.readAll();
    }

    @Override
    public Optional<ExchangeRate> getExchangeRateByCodes(String codes) {
        String baseCurrencyCode = getBaseCurrencyCode(codes);
        String targetCurrencyCode = getTargetCurrencyCode(codes);

        return exchangeRateDao.read(baseCurrencyCode, targetCurrencyCode);
    }

    @Override
    public ExchangeRateResponse getExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        //Optional<ExchangeRate> exchangeRateOptional = exchangeRateDao.read(baseCurrencyCode, targetCurrencyCode);
        return null;
    }
}
