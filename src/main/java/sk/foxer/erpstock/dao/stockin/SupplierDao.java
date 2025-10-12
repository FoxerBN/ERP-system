package sk.foxer.erpstock.dao.stockin;

import sk.foxer.erpstock.config.DatabaseConfig;
import sk.foxer.erpstock.mapper.stockin.SupplierMapper;
import sk.foxer.erpstock.model.stockin.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierDao {

    public static Supplier getByName(String name) {
        String sql = "SELECT * FROM suppliers WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return SupplierMapper.map(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
