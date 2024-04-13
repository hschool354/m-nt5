package newproject;

import java.sql.*;

public class Check {
    public static boolean kiemTraChucVu(String maNhanVien) {
        boolean coQuyen = false;
        String query = "SELECT MACV FROM NhanVien WHERE MANV = ? AND (MACV = 'CV1' OR MACV = 'CV6')";

        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maNhanVien);
            ResultSet resultSet = preparedStatement.executeQuery();

            coQuyen = resultSet.next();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return coQuyen;
    }
}
