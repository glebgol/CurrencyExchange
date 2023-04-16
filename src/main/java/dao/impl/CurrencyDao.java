package dao.impl;

import dao.ICurrencyDao;
import dao.impl.utlis.CurrencyDaoUtil;
import model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao implements ICurrencyDao {
    private final CurrencyDaoUtil daoUtil = new CurrencyDaoUtil();
    @Override
    public void create(Currency user) {

    }

    @Override
    public Currency read(int id) {
        final String query = "SELECT * FROM currencies WHERE id = (?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Currency currency = null;
            if (resultSet.next()) {
                currency = daoUtil.getCurrency(resultSet);
            }
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> readAll() {
        final String query = "SELECT * FROM currencies";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Currency> currencies = new ArrayList<>();
            while (resultSet.next()) {
                Currency currency = daoUtil.getCurrency(resultSet);
                currencies.add(currency);
            }
            return currencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Currency user) {

    }

    @Override
    public void delete(Currency userName) {

    }
}
