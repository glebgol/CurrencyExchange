import dao.ICurrencyDao;
import dao.IExchangeRateDao;
import dao.impl.CurrencyDao;
import dao.impl.ExchangeRateDao;

public class Main {
    private static final ICurrencyDao currencyDao = new CurrencyDao();
    private static final IExchangeRateDao exchangeRateDao = new ExchangeRateDao();

    public static void main(String[] args) {
//        System.out.println(exchangeRateDao.read(1));

        System.out.println(exchangeRateDao.readAll());

    }
}
