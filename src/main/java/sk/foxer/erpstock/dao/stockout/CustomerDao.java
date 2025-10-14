package sk.foxer.erpstock.dao.stockout;

import sk.foxer.erpstock.config.DatabaseConfig;
import sk.foxer.erpstock.mapper.stockout.CustomerMapper;
import sk.foxer.erpstock.model.stockout.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao {

    public static Customer getByName(String name) {
        String sql = "SELECT * FROM customers WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return CustomerMapper.map(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
