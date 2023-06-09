package dao.impl;

import dao.ICurrencyDao;
import dao.impl.utlis.CurrencyDaoUtil;
import model.Currency;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDao implements ICurrencyDao {
    private final CurrencyDaoUtil daoUtil = new CurrencyDaoUtil();
    private DataSource dataSource;

    private CurrencyDao() {
    }

    public CurrencyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Currency currency) {
        final String query = """
                INSERT INTO currencies (code, full_name, sign)
                VALUES (?, ?, ?)
                """;
        try (Connection connection = dataSource.getConnection();
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
    public Optional<Currency> read(int id) {
        final String query = "SELECT * FROM currencies WHERE id = (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Currency currency = null;
            if (resultSet.next()) {
                currency = daoUtil.getCurrency(resultSet);
            }
            return Optional.ofNullable(currency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Currency> read(String code) {
        final String query = "SELECT * FROM currencies WHERE code = (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();

            Currency currency = null;
            if (resultSet.next()) {
                currency = daoUtil.getCurrency(resultSet);
            }
            return Optional.ofNullable(currency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> readAll() {
        final String query = "SELECT * FROM currencies";
        try (Connection connection = dataSource.getConnection();
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
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void delete(Currency currency) {
        throw new RuntimeException("Not implemented");
    }
}
