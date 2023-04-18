package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class USDExchangeRatePairTest {
    @Spy
    USDExchangeRatePair usdExchangeRatePair;

    @Test
    void getRateFrom_USDtoBYNAndUSDtoRUB_To_BYNtoRUB() {
        BigDecimal expectedRate = BigDecimal.valueOf(0.030918);
        BigDecimal firstRate = BigDecimal.valueOf(1 / 2.53);
        BigDecimal secondRate = BigDecimal.valueOf(1 / 81.83);

        BigDecimal actualRate = usdExchangeRatePair.getRate(firstRate, secondRate);

        assertEquals(expectedRate, actualRate);
    }

    @Test
    void getRateFrom_USDtoRUBAndUSDtoEUR_To_RUBtoEUR() {
        BigDecimal expectedRate = BigDecimal.valueOf(89.923076);
        BigDecimal firstRate = BigDecimal.valueOf(1 / 81.83);
        BigDecimal secondRate = BigDecimal.valueOf(1 / 0.91);

        BigDecimal actualRate = usdExchangeRatePair.getRate(firstRate, secondRate);

        assertEquals(expectedRate, actualRate);
    }
}