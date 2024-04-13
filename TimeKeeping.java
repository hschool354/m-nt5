package newproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class TimeKeeping extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public TimeKeeping() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 555);
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
		contentPane.setLayout(null);
		
		JTable perTable = new JTable();
		perTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

		// Thanh cuộn cho bảng nhân sự
		JScrollPane scrollPane = new JScrollPane(perTable);
		scrollPane.setBounds(10, 11, 916, 358);
		contentPane.add(scrollPane);
		
		JButton markAttendanceButton = new JButton("Chấm công");
        markAttendanceButton.setBounds(10, 380, 150, 30);
        contentPane.add(markAttendanceButton);

        // Xử lý sự kiện khi người dùng nhấn nút "Chấm công"
        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý chấm công ở đây
                // Gọi các phương thức hoặc hàm xử lý chấm công
            }
        });
		
		try {
			Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
		    String query = "SELECT MANV,TENNV,MACV,MACV,NGAYNGHI FROM NhanVien";
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);

		    // Tạo model cho bảng hóa đơn
		    NonEditableTableModel billModel = new NonEditableTableModel();
		    ResultSetMetaData metaData = resultSet.getMetaData();
		    int columnCount = metaData.getColumnCount();
		    
		    // Thêm tên cột vào model
		    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		        billModel.addColumn(metaData.getColumnName(columnIndex));
		    }
		    
		    // Thêm dữ liệu từ ResultSet vào model
		    while (resultSet.next()) {
		        Object[] rowData = new Object[columnCount];
		        for (int i = 0; i < columnCount; i++) {
		            rowData[i] = resultSet.getObject(i + 1);
		        }
		        billModel.addRow(rowData);
		    }
		    
		    // Đặt model cho bảng hóa đơn
		    perTable.setModel(billModel);

		    // Đóng kết nối
		    resultSet.close();
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

}
