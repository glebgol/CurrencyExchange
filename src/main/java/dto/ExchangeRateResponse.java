package dto;

import lombok.Data;
import model.Currency;
import model.ExchangeRate;

import java.math.BigDecimal;

@Data
public class ExchangeRateResponse {
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;

    public ExchangeRateResponse(ExchangeRate exchangeRate, BigDecimal amount, BigDecimal convertedAmount) {
        baseCurrency = exchangeRate.getBaseCurrency();
        targetCurrency = exchangeRate.getTargetCurrency();
        rate = exchangeRate.getRate();
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }
}
