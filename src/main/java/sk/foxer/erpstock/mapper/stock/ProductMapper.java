package sk.foxer.erpstock.mapper.stock;

import sk.foxer.erpstock.model.stock.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {
    public static Product map(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("unit"),
                rs.getDouble("purchase_price"),
                rs.getDouble("sale_price"),
                rs.getInt("current_stock")
        );
    }
}
