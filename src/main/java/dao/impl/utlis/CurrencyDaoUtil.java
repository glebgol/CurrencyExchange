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

        return new Currency(id, code, fullName, sign);
    }
}
