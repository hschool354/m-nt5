package newproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.security.Timestamp;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import newproject.Menu;

public class EditP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtMaNV;
    private JTextField textField;
    
    
	public EditP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 555);
		setResizable(false);
		contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Sử dụng phép nội suy mặc định
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                
                // Tăng chất lượng vẽ
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Bật chế độ chống răng cưa
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Bật chế độ chống răng cưa cho văn bản
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            }
        };
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//Màu
        Color customColor1 = new Color(113, 170, 117);//Màu vàng
        Color customColor2 = new Color(246, 246, 246);//Màu nền nhập
		contentPane.setLayout(null);
		
		JPanel toppanel = new JPanel();
		toppanel.setBounds(0, 0, 1100, 117);
        toppanel.setLayout(null);
        toppanel.setBackground(customColor1);
        contentPane.add(toppanel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(261, 72, 1100, 45);
        toppanel.add(panel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(223, 84, 40, 33);
        toppanel.add(panel_2);
        
        //Bảng Đăng ký
        JLabel lblNewLabel = new JLabel("CHỈNH SỬA THÔNG TIN NHÂN VIÊN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(314, 11, 439, 50);
        toppanel.add(lblNewLabel);
        
      //Bảng nhập tên Mã Chức Vụ;
        txtMaNV = new JTextField();
        txtMaNV.setBounds(10, 128, 141, 29);
        txtMaNV.setText("Mã Nhân Viên");
        txtMaNV.setBackground(customColor2);
        txtMaNV.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaNV.getText().equals("Mã Nhân Viên")) {
                	txtMaNV.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaNV.getText().isEmpty()) {
                	txtMaNV.setText("Mã Nhân Viên");
                }
            }
        });
        txtMaNV.setColumns(10);
        contentPane.add(txtMaNV);
        
        
        // Bảng
        JTable perTable = new JTable();
		perTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

		// Thanh cuộn cho bảng hóa đơn
		JScrollPane scrollPane = new JScrollPane(perTable);
		scrollPane.setBounds(161, 117, 925, 401);
		contentPane.add(scrollPane);
		
		 JButton btnSea = new JButton("TÌM");
	        btnSea.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String maNV = txtMaNV.getText();
	                
	                // Kết nối đến cơ sở dữ liệu
	                try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password)) {
	                    // Tạo truy vấn SQL
	                    String sql = "SELECT * FROM NhanVien WHERE MANV = ?";
	                    PreparedStatement insertStatement = connection.prepareStatement(sql);
	                    insertStatement.setString(1, maNV);
	                    
	                    ResultSet rs = insertStatement.executeQuery();
	                    DefaultTableModel model = new DefaultTableModel();
	                    perTable.setModel(model);

	                    // Lấy thông tin metadata của ResultSet
	                    ResultSetMetaData metaData = rs.getMetaData();
	                    int columnCount = metaData.getColumnCount();
	                    
	                    // Kiểm tra nếu không có dòng nào trả về
	                    if (!rs.next()) {
	                        JOptionPane.showMessageDialog(contentPane, "Không tìm thấy nhân viên với mã " + maNV);
	                        return;
	                    }


	                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	                        model.addColumn(metaData.getColumnName(columnIndex));
	                    }
	                    
	                 // Đưa dữ liệu từ ResultSet vào model
	                    do {
	                        Object[] rowData = new Object[columnCount];
	                        for (int i = 0; i < columnCount; i++) {
	                            rowData[i] = rs.getObject(i + 1);
	                        }
	                        model.addRow(rowData);
	                    } while (rs.next());
	                    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });


	        btnSea.setBounds(33, 172, 81, 29);
	        contentPane.add(btnSea);
	        
	     // Add a button for saving changes
	        JButton btnSaveChanges = new JButton("LƯU THAY ĐỔI");
	        btnSaveChanges.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password)) {
	                    for (int i = 0; i < perTable.getRowCount(); i++) {
	                        String maNV = (String) perTable.getValueAt(i, 0);
	                        String tenNV = (String) perTable.getValueAt(i, 1);
	                        String maCV = (String) perTable.getValueAt(i, 2);
	                        String maCN = (String) perTable.getValueAt(i, 2);
	                        java.sql.Timestamp ngaySinhTimestamp = (java.sql.Timestamp) perTable.getValueAt(i, 3);
	                        boolean gioiTinh = (boolean) perTable.getValueAt(i, 4);
	                        String soDT = (String) perTable.getValueAt(i, 5);
	                        String email = (String) perTable.getValueAt(i, 6);
	                        String diaChi = (String) perTable.getValueAt(i, 7);
	                        java.sql.Timestamp ngayVaoTimestamp = (java.sql.Timestamp) perTable.getValueAt(i, 8);
	                        java.sql.Timestamp ngayNghiTimestamp = (java.sql.Timestamp) perTable.getValueAt(i, 9);

	                        // Tạo câu lệnh SQL cập nhật dựa trên các giá trị thay đổi
	                        String updateQuery = "UPDATE NhanVien SET TENNV = ?, MACV = ?, MACN = ?, NGAYSINH = ?, GIOITINH = ?, SODT = ?, EMAIL = ?, DIACHI = ?, NGAYVAO = ?, NGAYNGHI = ? WHERE MANV = ?";
	                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                        updateStatement.setString(1, tenNV);
	                        updateStatement.setString(2, maCV);
	                        updateStatement.setString(3, maCN);
	                        updateStatement.setTimestamp(4, ngaySinhTimestamp);
	                        updateStatement.setBoolean(5, gioiTinh);
	                        updateStatement.setString(6, soDT);
	                        updateStatement.setString(7, email);
	                        updateStatement.setString(8, diaChi);
	                        updateStatement.setTimestamp(9, ngayVaoTimestamp);
	                        updateStatement.setTimestamp(10, ngayNghiTimestamp);
	                        updateStatement.setString(11, maNV); // Bỏ qua cột MANV

	                        updateStatement.executeUpdate();
	                    }
	                    JOptionPane.showMessageDialog(contentPane, "Thay đổi đã được lưu vào cơ sở dữ liệu.");
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	        btnSaveChanges.setBounds(10, 212, 131, 29);
	        contentPane.add(btnSaveChanges);


	        JButton btnQL = new JButton("Quay Lại");
	        btnQL.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) { 
	        		setVisible(false);	
					Personnel personnel = new Personnel();
					personnel.setVisible(true);
	        	}
	        });
	        btnQL.setBounds(10, 252, 131, 29);
	        contentPane.add(btnQL);
	}
	
}