package newproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;


import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup checkBoxGroup;
	public static String selectedArea;

	public Settings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 462);
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
		
		JLabel lblNewLabel = new JLabel("SETTINGS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 379, 25);
        contentPane.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 15));
        
        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		saveSelectedArea();
        	}
        });
        btnNewButton.setBounds(10, 391, 89, 23);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Quay Lại");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		setVisible(false);	
				Menu menu = new Menu();
				menu.setVisible(true);
        	}
        });
        btnNewButton_1.setBounds(280, 391, 89, 23);
        contentPane.add(btnNewButton_1);

		// Khởi tạo nhóm checkbox
        checkBoxGroup = new ButtonGroup();
        selectedArea = null;
		
		// Kết nối với cơ sở dữ liệu và lấy danh sách khu vực
        try {
        	Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
            String query = "SELECT MAKV FROM KhuVuc";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            int y = 50; // Y position to start displaying khu vuc labels
            while (rs.next()) {
                String MAKV = rs.getString("MAKV");
                JCheckBox checkBox = new JCheckBox(MAKV);
                checkBox.setBounds(50, y, 200, 30); // Set position and size for checkbox
                contentPane.add(checkBox); // Add checkbox to content pane
                checkBoxGroup.add(checkBox); // Add checkbox to group
                y += 40; // Increase Y position for next label
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	// Lưu khu vực được chọn vào biến
	private void saveSelectedArea() {
	    selectedArea = null; // Reset biến khu vực được chọn
	    for (Enumeration<AbstractButton> buttons = checkBoxGroup.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	            selectedArea = button.getText(); // Lưu khu vực được chọn vào biến
	            break; // Dừng vòng lặp khi đã tìm thấy khu vực được chọn
	        }
	    }
	    
	    if (selectedArea != null) {
	        JOptionPane.showMessageDialog(null, "Khu vực đã chọn: " + selectedArea, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Không có khu vực nào được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	

}
