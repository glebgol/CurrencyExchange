import dao.ICurrencyDao;
import dao.IExchangeRateDao;
import dao.impl.CurrencyDao;
import dao.impl.ExchangeRateDao;
import services.ICurrencyService;
import services.impl.CurrencyService;

public class Main {
    private static final ICurrencyDao currencyDao = new CurrencyDao();
    private static final IExchangeRateDao exchangeRateDao = new ExchangeRateDao();

    public static void main(String[] args) {
        ICurrencyService currencyService = new CurrencyService(new CurrencyDao());
        System.out.println(currencyService.getAllCurrencies());
        System.out.println(currencyService.getCurrencyByCode("USD"));
    }
}
