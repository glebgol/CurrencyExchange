package dao.impl.utlis;

import model.Currency;
import model.ExchangeRate;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateDaoUtil {
    public ExchangeRate getExchangeRate(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int baseCurrencyId = resultSet.getInt("base_currency_id");
        int targetCurrencyId = resultSet.getInt("target_currency_id");
        BigDecimal rate = resultSet.getBigDecimal("rate");
        String baseCurrencyCode = resultSet.getString(6);
        String baseCurrencyFullName = resultSet.getString(7);
        String baseCurrencySign = resultSet.getString(8);

        String targetCurrencyCode = resultSet.getString(10);
        String targetCurrencyFullName = resultSet.getString(11);
        String targetCurrencySign = resultSet.getString(12);

        Currency baseCurrency = new Currency();
        baseCurrency.setCode(baseCurrencyCode);
        baseCurrency.setId(baseCurrencyId);
        baseCurrency.setFullName(baseCurrencyFullName);
        baseCurrency.setSign(baseCurrencySign);

        Currency targetCurrency = new Currency();
        targetCurrency.setCode(targetCurrencyCode);
        targetCurrency.setId(targetCurrencyId);
        targetCurrency.setFullName(targetCurrencyFullName);
        targetCurrency.setSign(targetCurrencySign);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(id);
        exchangeRate.setRate(rate);
        exchangeRate.setBaseCurrency(baseCurrency);
        exchangeRate.setTargetCurrency(targetCurrency);
        return exchangeRate;
    }
}
