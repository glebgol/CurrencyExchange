package listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dao.ICurrencyDao;
import dao.IExchangeRateDao;
import dao.impl.CurrencyDao;
import dao.impl.ExchangeRateDao;
import services.ICurrencyService;
import services.IExchangeRateService;
import services.impl.CurrencyService;
import services.impl.ExchangeRateService;
import utils.ICurrencyRequestParser;
import utils.ICurrencyRequestValidator;
import utils.IJsonMapper;
import utils.impl.CurrencyRequestParser;
import utils.impl.CurrencyRequestValidator;
import utils.impl.JsonMapper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    public ContextListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = makeHikariDataSource(context);

        ICurrencyDao currencyDao = new CurrencyDao(dataSource);
        ICurrencyService currencyService = new CurrencyService(currencyDao);

        IExchangeRateDao exchangeRateDao = new ExchangeRateDao(dataSource);
        IExchangeRateService exchangeRateService = new ExchangeRateService(exchangeRateDao);

        IJsonMapper jsonMapper = new JsonMapper();
        ICurrencyRequestParser currencyRequestParser = new CurrencyRequestParser();
        ICurrencyRequestValidator currencyRequestValidator = new CurrencyRequestValidator();

        context.setAttribute("currencyService", currencyService);
        context.setAttribute("exchangeRateService", exchangeRateService);
        context.setAttribute("jsonMapper", jsonMapper);
        context.setAttribute("currencyRequestParser", currencyRequestParser);
        context.setAttribute("currencyRequestValidator", currencyRequestValidator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private HikariDataSource makeHikariDataSource(ServletContext context) {
        try {
            HikariConfig config = new HikariConfig();

            Properties properties = new Properties();
            properties.load(context.getResourceAsStream("WEB-INF/db.properties"));

            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setDriverClassName(properties.getProperty("db.driver.name"));

            return new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
