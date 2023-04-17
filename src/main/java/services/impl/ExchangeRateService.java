package services.impl;

import dao.IExchangeRateDao;
import model.ExchangeRate;
import services.IExchangeRateService;

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

        ExchangeRate exchangeRate = exchangeRateDao.read(baseCurrencyCode, targetCurrencyCode);

        return Optional.of(exchangeRate);
    }
}
