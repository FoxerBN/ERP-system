package sk.foxer.erpstock.mapper.stockin;

import sk.foxer.erpstock.model.stockin.StockIn;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockInMapper {
    public static StockIn map(ResultSet rs) throws SQLException {
        return new StockIn(
                rs.getInt("id"),
                rs.getDate("date").toLocalDate(),
                rs.getString("supplier_name"),
                rs.getString("product_name"),
                rs.getDouble("quantity"),
                rs.getDouble("unit_price"),
                rs.getDouble("total")
        );
    }
}
