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
    public void create(Currency currency) {
        final String query = """
                INSERT INTO currencies (code, full_name, sign)
                VALUES (?, ?, ?)
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            String code = currency.getCode();
            String fullName = currency.getFullName();
            String sign = currency.getSign();

            statement.setString(1, code);
            statement.setString(2, fullName);
            statement.setString(3, sign);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public Currency read(String code) {
        final String query = "SELECT * FROM currencies WHERE code = (?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:currency_exchanger.db");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
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
    public void update(Currency currency) {

    }

    @Override
    public void delete(Currency currency) {

    }
}
