package sk.foxer.erpstock.mapper.stockin;

import sk.foxer.erpstock.model.stockin.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper {
    public static Supplier map(ResultSet rs) throws SQLException {
        return new Supplier(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("contact")
        );
    }
}
