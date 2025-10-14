package sk.foxer.erpstock.dao.stockout;

import sk.foxer.erpstock.config.DatabaseConfig;
import sk.foxer.erpstock.mapper.stockout.StockOutMapper;
import sk.foxer.erpstock.model.stockout.StockOut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockOutDao {

    public static List<StockOut> getAll() {
        List<StockOut> list = new ArrayList<>();

        String sql = """
            SELECT so.id,
                   so.date,
                   c.name AS customer_name,
                   p.name AS product_name,
                   so.quantity,
                   so.unit_price,
                   (so.quantity * so.unit_price) AS total_price
            FROM stock_out so
            JOIN customers c ON so.customer_id = c.id
            JOIN products p ON so.product_id = p.id
            ORDER BY so.date DESC, so.id DESC;
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(StockOutMapper.map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /** Načíta výdaje podľa konkrétneho zákazníka (customer_id) */
    public static List<StockOut> getByCustomerId(int customerId) {
        List<StockOut> list = new ArrayList<>();

        String sql = """
            SELECT so.id,
                   so.date,
                   c.name AS customer_name,
                   p.name AS product_name,
                   so.quantity,
                   so.unit_price,
                   (so.quantity * so.unit_price) AS total_price
            FROM stock_out so
            JOIN customers c ON so.customer_id = c.id
            JOIN products p ON so.product_id = p.id
            WHERE so.customer_id = ?
            ORDER BY so.date DESC, so.id DESC;
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(StockOutMapper.map(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
