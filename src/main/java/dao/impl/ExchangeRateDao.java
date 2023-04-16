package dao.impl;

import dao.IExchangeRateDao;
import dao.impl.utlis.ExchangeRateDaoUtil;
import model.ExchangeRate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateDao implements IExchangeRateDao {
    private final ExchangeRateDaoUtil daoUtil = new ExchangeRateDaoUtil();
    @Override
    public void create(ExchangeRate user) {

    }

    @Override
    public ExchangeRate read(int id) {
        final String query = "SELECT * FROM exchange_rates e " +
                "JOIN currencies c ON c.id = e.base_currency_id " +
                "JOIN currencies c2 ON c2.id = e.target_currency_id " +
                "WHERE e.id = (?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            ExchangeRate exchangeRate = null;
            if (resultSet.next()) {
                exchangeRate = daoUtil.getExchangeRate(resultSet);
            }
            return exchangeRate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExchangeRate> readAll() {
        final String query = "SELECT * FROM exchange_rates e " +
                "JOIN currencies c ON c.id = e.base_currency_id " +
                "JOIN currencies c2 ON c2.id = e.target_currency_id ";
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
    public void update(ExchangeRate user) {

    }

    @Override
    public void delete(ExchangeRate userName) {

    }
}
