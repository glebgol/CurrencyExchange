package dao.impl;

import dao.IExchangeRateDao;
import dao.impl.utlis.ExchangeRateDaoUtil;
import model.ExchangeRate;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRateDao implements IExchangeRateDao {
    private final ExchangeRateDaoUtil daoUtil = new ExchangeRateDaoUtil();
    @Override
    public void create(ExchangeRate exchangeRate) {
        final String query = """
                INSERT INTO exchange_rates (base_currency_id, target_currency_id, rate)
                VALUES (?, ?, ?)
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            int baseCurrencyId = exchangeRate.getBaseCurrency().getId();
            int targetCurrencyId = exchangeRate.getTargetCurrency().getId();
            BigDecimal rate = exchangeRate.getRate();

            statement.setInt(1, baseCurrencyId);
            statement.setInt(2, targetCurrencyId);
            statement.setBigDecimal(3, rate);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ExchangeRate> read(int id) {
        final String query = """
                SELECT * FROM exchange_rates e
                JOIN currencies c ON c.id = e.base_currency_id
                JOIN currencies c2 ON c2.id = e.target_currency_id
                WHERE e.id = (?)
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            ExchangeRate exchangeRate = null;
            if (resultSet.next()) {
                exchangeRate = daoUtil.getExchangeRate(resultSet);
            }
            return Optional.ofNullable(exchangeRate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ExchangeRate> read(String baseCurrencyCode, String targetCurrencyCode) {
        final String query = """
                SELECT * FROM exchange_rates e
                JOIN currencies c ON c.id = e.base_currency_id
                JOIN currencies c2 ON c2.id = e.target_currency_id
                WHERE c.code = (?) AND c2.code = (?)
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, baseCurrencyCode);
            statement.setString(2, targetCurrencyCode);

            ResultSet resultSet = statement.executeQuery();

            ExchangeRate exchangeRate = null;
            if (resultSet.next()) {
                exchangeRate = daoUtil.getExchangeRate(resultSet);
            }
            return Optional.ofNullable(exchangeRate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExchangeRate> readAll() {
        final String query = """
                SELECT * FROM exchange_rates e
                JOIN currencies c ON c.id = e.base_currency_id
                JOIN currencies c2 ON c2.id = e.target_currency_id
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             Statement statement = connection.createStatement()) {

            List<ExchangeRate> exchangeRates = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ExchangeRate exchangeRate = daoUtil.getExchangeRate(resultSet);
                exchangeRates.add(exchangeRate);
            }

            return exchangeRates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ExchangeRate exchangeRate) {

    }

    @Override
    public void delete(ExchangeRate exchangeRate) {

    }
}
