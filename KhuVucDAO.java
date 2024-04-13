package newproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class KhuVucDAO {


    public Map<String, Double> getHesoGia() {
        Map<String, Double> hesoGiaMap = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password)) {
            String query = "SELECT MAKV, HESOGIA FROM KhuVuc";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maKV = resultSet.getString("MAKV");
                double hesoGia = resultSet.getDouble("HESOGIA");
                hesoGiaMap.put(maKV, hesoGia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hesoGiaMap;
    }
}
