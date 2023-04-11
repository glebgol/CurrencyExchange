package dto;

import lombok.Data;
import model.Currency;

import java.math.BigDecimal;

@Data
public class ExchangeRateDTO {
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;
}
