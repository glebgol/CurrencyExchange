package dto;

import lombok.Builder;
import model.Currency;

import java.math.BigDecimal;

@Builder
public class ExchangeRateResponse {
    private Currency baseCurrency;
    private Currency targetCurrency;
    BigDecimal rate;
    BigDecimal amount;
    BigDecimal convertedAmount;
}
