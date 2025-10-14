package sk.foxer.erpstock.mapper.stockout;

import sk.foxer.erpstock.model.stockout.StockOut;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockOutMapper {
    public static StockOut map(ResultSet rs) throws SQLException {
        return new StockOut(
                rs.getInt("id"),
                rs.getDate("date").toLocalDate(),
                rs.getString("customer_name"),
                rs.getString("product_name"),
                rs.getDouble("quantity"),
                rs.getDouble("unit_price"),
                rs.getDouble("total_price")
        );
    }
}
