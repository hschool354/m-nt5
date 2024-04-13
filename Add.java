package newproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

import newproject.Menu;

public class Add extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtMaCV;
    private JTextField txtBDate;
    private JTextField txtGender;
    private JTextField txtsdt;
    private JTextField txtgmail;
    private JTextField txtDC;
    private JTextField passwordField;
    private JButton btnNewButton_1;
    private JComboBox<String> genderComboBox;
    private JTextField txtMaCN;

    
	public Add() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 719, 510);
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
        contentPane.setForeground(new Color(128, 128, 64));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Màu
        Color customColor1 = new Color(113, 170, 117);//Màu vàng
        Color customColor2 = new Color(246, 246, 246);//Màu nền nhập
		
		JPanel toppanel = new JPanel();
        toppanel.setLayout(null);
        toppanel.setBackground(customColor1);
        toppanel.setBounds(0, 0, 705, 117);
        contentPane.add(toppanel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(261, 72, 455, 45);
        toppanel.add(panel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(223, 84, 40, 33);
        toppanel.add(panel_2);
        
        //Bảng Đăng ký
        JLabel lblNewLabel = new JLabel("THÊM NHÂN VIÊN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(20, 45, 130, 23);
        toppanel.add(lblNewLabel);
        
        
        //Bảng nhập tên tài khoản
        txtUsername = new JTextField();
        txtUsername.setText("Username");
        txtUsername.setBounds(10, 148, 306, 37);
        txtUsername.setBackground(customColor2);
        txtUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsername.getText().equals("Username")) {
                    txtUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsername.getText().isEmpty()) {
                    txtUsername.setText("Username");
                }
            }
        });
        txtUsername.setColumns(10);
        contentPane.add(txtUsername);
        
        
        //Bảng nhập tên Mã Chức Vụ;
        txtMaCV = new JTextField();
        txtMaCV.setText("Mã Chức Vụ");
        txtMaCV.setBounds(10,196,306,37);
        txtMaCV.setBackground(customColor2);
        txtMaCV.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaCV.getText().equals("Mã Chức Vụ")) {
                	txtMaCV.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaCV.getText().isEmpty()) {
                	txtMaCV.setText("Mã Chức Vụ");
                }
            }
        });
        txtMaCV.setColumns(10);
        contentPane.add(txtMaCV);
        
        
        //Bảng nhập tên Ngày Sinh
        txtBDate = new JTextField();
        txtBDate.setText("Ngày Sinh");
        txtBDate.setBounds(10,244,306,37);
        txtBDate.setBackground(customColor2);
        txtBDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBDate.getText().equals("Ngày Sinh")) {
                	txtBDate.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBDate.getText().isEmpty()) {
                	txtBDate.setText("Ngày Sinh");
                }
            }
        });
        txtBDate.setColumns(10);
        contentPane.add(txtBDate);
		
        
        //Nhập gmail
        txtgmail = new JTextField();
        txtgmail.setText("Gmail");
        txtgmail.setBounds(10, 340, 306, 37);
        txtgmail.setBackground(customColor2);
        txtgmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtgmail.getText().equals("Gmail")) {
                	txtgmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtgmail.getText().isEmpty()) {
                	txtgmail.setText("Gmail");
                }
            }
        });
        txtgmail.setColumns(10);
        contentPane.add(txtgmail);
        
        
        //Nhập sdt
        txtsdt = new JTextField();
        txtsdt.setText("Số điện thoại");
        txtsdt.setBounds(10, 292, 306, 37);
        txtsdt.setBackground(customColor2);
        txtsdt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtsdt.getText().equals("Số điện thoại")) {
                	txtsdt.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtsdt.getText().isEmpty()) {
                	txtsdt.setText("Số điện thoại");
                }
            }
        });
        txtsdt.setColumns(10);
        contentPane.add(txtsdt);
        
        
        //NÚT nhập địa chỉ
        txtDC = new JTextField();
        txtDC.setText("Địa chỉ");
        txtDC.setBounds(327, 148, 306, 37);
        txtDC.setBackground(customColor2);
        txtDC.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDC.getText().equals("Địa Chỉ")) {
                	txtDC.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtDC.getText().isEmpty()) {
                	txtDC.setText("Địa chỉ");
                }
            }
        });
        txtDC.setColumns(10);
        contentPane.add(txtDC);
        
        txtMaCN = new JTextField();
        txtMaCN.setText("Mã Chi Nhánh");
        txtMaCN.setBounds(327, 196, 306, 37);
        txtMaCN.setBackground(customColor2);
        txtMaCN.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaCN.getText().equals("Mã Chi Nhánh")) {
                	txtMaCN.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaCN.getText().isEmpty()) {
                	txtMaCN.setText("Mã Chi Nhánh");
                }
            }
        });
        txtMaCN.setColumns(10);
        contentPane.add(txtMaCN);
        
     // Tạo JComboBox cho giới tính
        String[] genders = {"Nam", "Nữ"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(326, 247, 150, 30);
        contentPane.add(genderComboBox);
        
     // Xử lý sự kiện khi chọn giới tính
        genderComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn từ JComboBox
                String selectedGender = (String) genderComboBox.getSelectedItem();
                
                // Chuyển đổi giới tính thành giá trị 1 hoặc 0
                int genderValue = selectedGender.equals("Nam") ? 1 : 0;
                
                // Lưu giá trị vào cơ sở dữ liệu hoặc thực hiện các xử lý khác
                // Gọi phương thức performHome() hoặc thực hiện xử lý trực tiếp ở đây
            }
        });
        
        JButton btnQL = new JButton("Quay Lại");
        btnQL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		setVisible(false);	
				Personnel personnel = new Personnel();
				personnel.setVisible(true);
        	}
        });
        btnQL.setBounds(220, 411, 96, 29);
        contentPane.add(btnQL);
        
        // NÚT ĐĂNG KÝ
        JButton btnNewButton = new JButton("Thêm Nhân Viên");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performHome();
        	}
        });
        btnNewButton.setBounds(10, 414, 96, 23);
        btnNewButton.setBackground(Color.BLACK); 
        btnNewButton.setForeground(SystemColor.textHighlightText);
        contentPane.add(btnNewButton);
        
   
	}
	
	
	
	private String newMANV() {
	    String latestID = "NV00"; // Mã nhân viên mặc định
	    String query = "SELECT MAX(MANV) FROM NhanVien"; // Truy vấn lấy mã nhân viên lớn nhất từ cơ sở dữ liệu

	    try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {

	        if (resultSet.next()) {
	            String maxID = resultSet.getString(1); // Lấy mã nhân viên lớn nhất từ kết quả truy vấn
	            if (maxID != null) {
	                // Loại bỏ các khoảng trắng không mong muốn trước khi chuyển đổi sang số nguyên
	                maxID = maxID.trim();
	                int num = Integer.parseInt(maxID.substring(2)) + 1;
	                latestID = String.format("NV%02d", num); // Format lại mã nhân viên mới
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return latestID;
	}

	
	public void performHome() {
        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password)) {
            String inputMANV = newMANV();
            String inputUsername = txtUsername.getText();
            String inputMaCV = txtMaCV.getText();
            String inputBDate = txtBDate.getText();
            String inputsdt = txtsdt.getText();
            String inputgmail = txtgmail.getText();
            String inputDC = txtDC.getText();
            String inputMACN = txtMaCN.getText();
            String selectedGender = (String) genderComboBox.getSelectedItem();
            int genderValue = selectedGender.equals("Nam") ? 1 : 0;
            
            LocalDate today = LocalDate.now();

            String insertQuery = "INSERT INTO NhanVien (MANV, TENNV, MACV, MACN, NGAYSINH, GIOITINH, SODT, EMAIL, DIACHI, NGAYVAO, NGAYNGHI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, inputMANV);
                insertStatement.setString(2, inputUsername);
                insertStatement.setString(3, inputMaCV);
                insertStatement.setString(4, inputMACN);
                insertStatement.setString(5, inputBDate);
                insertStatement.setInt(6, genderValue);
                insertStatement.setString(7, inputsdt);
                insertStatement.setString(8, inputgmail);
                insertStatement.setString(9, inputDC);
                insertStatement.setString(10, today.toString()); // Ngày vào là ngày hiện tại
                insertStatement.setString(11, null); // Change to a valid date or adjust as needed

                int rowsAffected = insertStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Registration successful! MaNV: " + inputMANV);
                    setVisible(false);	
					Personnel personnel = new Personnel();
					personnel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}