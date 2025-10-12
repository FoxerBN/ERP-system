package sk.foxer.erpstock.dao.stock;

import sk.foxer.erpstock.config.DatabaseConfig;
import sk.foxer.erpstock.model.stock.Product;
import sk.foxer.erpstock.mapper.stock.ProductMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(ProductMapper.map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
