package sk.foxer.erpstock.mapper.stockout;

import sk.foxer.erpstock.model.stockout.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public static Customer map(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("contact")
        );
    }
}
