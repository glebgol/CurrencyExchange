package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateRequest {
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    BigDecimal amountToConvert;
}
