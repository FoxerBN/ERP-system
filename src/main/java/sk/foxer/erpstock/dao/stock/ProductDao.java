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

    public boolean addProduct(Product product) {
        String sql = """
                INSERT INTO products (code, name, unit, purchase_price, sale_price, current_stock)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getUnit());
            stmt.setDouble(4, product.getPurchasePrice());
            stmt.setDouble(5, product.getSalePrice());
            stmt.setInt(6, product.getCurrentStock());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        product.setId(keys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateProduct(Product product) {
        String sql = """
                UPDATE products
                SET code = ?, name = ?, unit = ?, purchase_price = ?, sale_price = ?, current_stock = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getUnit());
            stmt.setDouble(4, product.getPurchasePrice());
            stmt.setDouble(5, product.getSalePrice());
            stmt.setInt(6, product.getCurrentStock());
            stmt.setInt(7, product.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
