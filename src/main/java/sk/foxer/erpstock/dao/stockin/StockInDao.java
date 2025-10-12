package sk.foxer.erpstock.dao.stockin;

import sk.foxer.erpstock.config.DatabaseConfig;
import sk.foxer.erpstock.mapper.stockin.StockInMapper;
import sk.foxer.erpstock.model.stockin.StockIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockInDao {

    public static List<StockIn> getAll() {
        List<StockIn> list = new ArrayList<>();
        String sql = """
            SELECT si.id, si.date, s.name AS supplier_name, p.name AS product_name,
                   si.quantity, si.unit_price, si.total
            FROM stock_in si
            JOIN suppliers s ON si.supplier_id = s.id
            JOIN products p ON si.product_id = p.id
            ORDER BY si.date DESC;
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(StockInMapper.map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
