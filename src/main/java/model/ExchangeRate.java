package model;

import lombok.Data;

@Data
public class ExchangeRate {
    private int id;
    private Currency baseCurrency;
    private Currency targetCurrency;
}
