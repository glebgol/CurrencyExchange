package dao.impl.utlis;

import model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDaoUtil {
    public Currency getCurrency(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String code = resultSet.getString("code");
        String fullName = resultSet.getString("full_name");
        String sign = resultSet.getString("sign");

        Currency currency = new Currency();
        currency.setFullName(fullName);
        currency.setId(id);
        currency.setSign(sign);
        currency.setCode(code);
        return currency;
    }
}
