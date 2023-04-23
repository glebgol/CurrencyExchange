package services.impl;

import dao.IExchangeRateDao;
import dto.ExchangeRateRequest;
import dto.ExchangeRateResponse;
import model.ExchangeRate;
import model.USDExchangeRatePair;
import services.IExchangeRateService;
import services.impl.utils.ExchangeCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static services.impl.utils.CodesUtil.*;

public class ExchangeRateService implements IExchangeRateService {
    private final IExchangeRateDao exchangeRateDao;
    private final ExchangeCalculator exchangeCalculator;
    public ExchangeRateService(IExchangeRateDao exchangeRateDao) {
        this.exchangeRateDao = exchangeRateDao;
        this.exchangeCalculator = new ExchangeCalculator();
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
    public Optional<ExchangeRateResponse> getExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        String baseCurrencyCode = exchangeRateRequest.getBaseCurrencyCode();
        String targetCurrencyCode = exchangeRateRequest.getTargetCurrencyCode();
        BigDecimal amountToConvert = exchangeRateRequest.getAmountToConvert();

        Optional<ExchangeRate> exchangeRateOptional = exchangeRateDao.read(baseCurrencyCode, targetCurrencyCode);
        if (exchangeRateOptional.isPresent()) {
            ExchangeRate exchangeRate = exchangeRateOptional.get();
            BigDecimal convertedAmount = exchangeCalculator.convertFromBaseToTarget(amountToConvert, exchangeRate);

            return Optional.of(new ExchangeRateResponse(exchangeRate, amountToConvert, convertedAmount));
        }

        exchangeRateOptional = exchangeRateDao.read(targetCurrencyCode, baseCurrencyCode);
        if (exchangeRateOptional.isPresent()) {
            ExchangeRate exchangeRate = exchangeRateOptional.get().getReverseExchangeRate();
            BigDecimal convertedAmount = exchangeCalculator.convertFromBaseToTarget(amountToConvert, exchangeRate);

            return Optional.of(new ExchangeRateResponse(exchangeRate, amountToConvert, convertedAmount));
        }

        Optional<USDExchangeRatePair> usdExchangeRatePairOptional =
                exchangeRateDao.readCodesWithUSDBase(baseCurrencyCode, targetCurrencyCode);
        if (usdExchangeRatePairOptional.isPresent()) {
            USDExchangeRatePair usdExchangeRatePair = usdExchangeRatePairOptional.get();
            ExchangeRate exchangeRate = usdExchangeRatePair.getExchangeRateFromFirstToSecond();
            BigDecimal convertedAmount = exchangeCalculator.convertFromUSDPair(amountToConvert, usdExchangeRatePair);

            return Optional.of(new ExchangeRateResponse(exchangeRate, amountToConvert, convertedAmount));
        }
        return Optional.empty();
    }

    @Override
    public void addExchangeRate(ExchangeRate exchangeRate) {
        exchangeRateDao.create(exchangeRate);
    }
}
