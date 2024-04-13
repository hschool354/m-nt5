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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import newproject.Menu;

public class DeleteP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtMaNV;
    private JTextField textField;
    
    private static String MANVD;
    
    
	public DeleteP() {
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
        JLabel lblNewLabel = new JLabel("XÓA NHÂN VIÊN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(20, 45, 130, 23);
        toppanel.add(lblNewLabel);
        
      //Bảng nhập tên Mã Chức Vụ;
        txtMaNV = new JTextField();
        txtMaNV.setBounds(10, 128, 141, 29);
        txtMaNV.setText("Mã Chức Vụ");
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
	        
	     // nút xóa
	        JButton btndel = new JButton("XÓA");
	        btndel.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) { 
	        		MANVD = txtMaNV.getText();
	                AccpectD accpectD = new AccpectD(); 
	                accpectD.setVisible(true);
	        	}
	        });
	        btndel.setBounds(33, 224, 81, 29);
	        contentPane.add(btndel);
		
	        
	        JButton btnQL = new JButton("Quay Lại");
	        btnQL.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) { 
	        		setVisible(false);	
					Personnel personnel = new Personnel();
					personnel.setVisible(true);
	        	}
	        });
	        btnQL.setBounds(33, 277, 81, 29);
	        contentPane.add(btnQL);
	}
	
	public static String layMaNhanVien() {
        return MANVD;
    }
	
}