package newproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AccpectD extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String maNV;;
	
	

	public AccpectD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 161);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bạn có chắc chắn muốn xóa");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 27, 222, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnY = new JButton("Không");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				DeleteP delete = new DeleteP();
				delete.setVisible(true);
			}
		});
        btnY.setBounds(136, 66, 96, 23);
        btnY.setBackground(Color.BLACK); 
        btnY.setForeground(SystemColor.textHighlightText);
        contentPane.add(btnY);
        
        JButton btnN = new JButton("Có");
        btnN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Thực hiện xóa nhân viên
                String maNV = DeleteP.layMaNhanVien();
                try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password)) {
                    // Tạo truy vấn SQL để xóa nhân viên
                    String sql = "DELETE FROM NhanVien WHERE MANV = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, maNV);

                    // Thực thi truy vấn xóa
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Nhân viên đã được xóa thành công!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // Đóng cửa sổ sau khi xóa
                setVisible(false);
            }
        });
        btnN.setBounds(10, 66, 96, 23);
        btnN.setBackground(Color.BLACK); 
        btnN.setForeground(SystemColor.textHighlightText);
        contentPane.add(btnN);
	}


}