package dao.impl;

import dao.ICurrencyDao;
import model.Currency;

import java.sql.*;
import java.util.List;

public class CurrencyDao implements ICurrencyDao {
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

            String code = resultSet.getString("code");
            String fullName = resultSet.getString("full_name");
            String sign = resultSet.getString("sign");
            Currency currency = null;
            if (resultSet.next()) {
                currency = new Currency();
                currency.setFullName(fullName);
                currency.setId(id);
                currency.setSign(sign);
                currency.setCode(code);
            }
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> readAll() {
        return null;
    }

    @Override
    public void update(Currency user) {

    }

    @Override
    public void delete(Currency userName) {

    }
}
