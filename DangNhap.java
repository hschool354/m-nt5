package newproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class DangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JPasswordField passwordField;
    private JTextField txtUsername;
    private JButton btnNewButton_1;
    
    private static String maNhanVien;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {	
                    Start frame = new Start();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    
    public void performHome() {
        
        String inputUsername = txtUsername.getText();
        String inputPassword = passwordField.getText();
        
        
        try {
        	Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);

            //kiểm tra tài khoản và mật khẩu
            String query = "SELECT * FROM NHANVIEN WHERE MANV=? AND MANV=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, inputUsername);
                preparedStatement.setString(2, inputPassword);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    
                	maNhanVien = resultSet.getString("MANV");
                	
                	setVisible(false);	
    				Home home = new Home();
    				home.setVisible(true);
                   
                    
                } else {
                    // Tài khoản hoặc mật khẩu không hợp lệ
                    JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Đóng kết nối
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
	}

    public static String layMaNhanVien() {
        return maNhanVien;
    }
    
    public DangNhap() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 340, 510);
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
        Color customColor1 = new Color(6,118,85);//Màu xanh nhạt
        Color customColor2 = new Color(246, 246, 246);//Màu nền nhập
        
        
        //Nhập Tên tài khoản
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
                String input = txtUsername.getText().trim(); // Loại bỏ khoảng trắng
                if (input.isEmpty()) {
                    txtUsername.setText("Username");
                } else {
                    txtUsername.setText(input); // Gán lại giá trị đã được loại bỏ khoảng trắng
                }
            }
        });
        txtUsername.setColumns(10);
        contentPane.add(txtUsername);
        
        
        //Nút đăng nhập
        JButton btnLogin = new RoundButton("Sign In");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performHome();
        	}
        });
        btnLogin.setBounds(113, 276, 96, 23);
        btnLogin.setBackground(Color.BLACK); 
        btnLogin.setForeground(SystemColor.textHighlightText);
        contentPane.add(btnLogin);
        
        //khung màu vàng
        JPanel toppanel = new JPanel();
        toppanel.setLayout(null);
        toppanel.setBackground(customColor1);
        toppanel.setBounds(0, 0, 326, 117);
        contentPane.add(toppanel);
        
        
        
        //Nút quên mật khẩu
        JButton btnNewButton_2 = new JButton("Forgot password?");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.setBounds(175, 244, 141, 23);
        contentPane.add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnNewButton_2.setBorderPainted(false);
        
        
        //Bảng Đăng nhập
        JLabel lblNewLabel = new JLabel("SIGN IN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(20, 45, 76, 23);
        toppanel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(261, 72, 65, 45);
        toppanel.add(panel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(223, 84, 40, 33);
        toppanel.add(panel_2);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 313, 326, 10);
        panel.setBackground(customColor2);
        contentPane.add(panel);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 450, 500);
        contentPane.add(layeredPane);
        
        
        //Đăng nhập với gg
        JLabel lblNewLabel_1 = new JLabel("");
        ImageIcon icon = new ImageIcon("D:\\image\\gg.png");
        Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lblNewLabel_1.setIcon(new ImageIcon(image));
        lblNewLabel_1.setBounds(21, 352, 39, 45); // Sửa kích thước label
        layeredPane.add(lblNewLabel_1, JLayeredPane.PALETTE_LAYER);
        
        
        JButton btnNewButton_3 = new JButton("Continue With Google");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);	
				comingsoon comingsoon1 = new comingsoon();
				comingsoon1.setVisible(true);
        	}
        });
        btnNewButton_3.setBackground(new Color(255, 255, 255));
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_3.setBounds(10, 352, 306, 45);
        layeredPane.add(btnNewButton_3, JLayeredPane.DEFAULT_LAYER);
        
        
        // Đăng nhập với fb
        JLabel lblNewLabel_2 = new JLabel("");
        ImageIcon icon1 = new ImageIcon("D:\\image\\fb.png");
        Image image1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lblNewLabel_2.setIcon(new ImageIcon(image1));
        lblNewLabel_2.setBounds(21, 408, 39, 45); // Sửa kích thước label
        layeredPane.add(lblNewLabel_2, JLayeredPane.PALETTE_LAYER);
        
        
        JButton btnNewButton_4 = new JButton("Continue With Facebook");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			setVisible(false);	
					comingsoon comingsoon1 = new comingsoon();
					comingsoon1.setVisible(true);
        	}
        });
        btnNewButton_4.setBackground(new Color(255, 255, 255));
        btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_4.setBounds(10, 408, 306, 45);
        layeredPane.add(btnNewButton_4, JLayeredPane.DEFAULT_LAYER);
        
        JLabel lblNewLabel_3 = new JLabel("");
        ImageIcon icon3 = new ImageIcon("D:\\image\\pw.png");
        Image image2 = icon3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lblNewLabel_3.setIcon(new ImageIcon(image2));
        lblNewLabel_3.setBounds(270, 203, 39, 23); // Sửa kích thước label
        layeredPane.add(lblNewLabel_3, JLayeredPane.PALETTE_LAYER);
        
      //Phần mật khẩu
                // Toggle button for showing/hiding password
        JToggleButton toggleButton = new JToggleButton("");
        toggleButton.setBackground(new Color(246, 246, 246));
        toggleButton.setBounds(270, 203, 39, 23);
        toggleButton.setBorderPainted(false);
        layeredPane.add(toggleButton);
                
        JToggleButton tglbtnNewToggleButton = new JToggleButton("");
        tglbtnNewToggleButton.setBounds(270, 203, 39, 23);
        tglbtnNewToggleButton.setBackground(customColor2);
        tglbtnNewToggleButton.setBorderPainted(false);
        layeredPane.add(tglbtnNewToggleButton);
                

        passwordField = new JPasswordField();
        passwordField.setBounds(10, 196, 306, 37);
        layeredPane.add(passwordField);
        passwordField.setText("Password");
        passwordField.setBackground(customColor2);
        passwordField.setEchoChar((char) 0);
                
                        // Listener for focusing on the password field
                passwordField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                char[] password = passwordField.getPassword();
                                if (password.length > 0 && String.valueOf(password).equals("Password")) {
                                	passwordField.setText("");
                                	passwordField.setEchoChar('*');
                                }
                            }
                
                            @Override
                            public void focusLost(FocusEvent e) {
                                char[] password = passwordField.getPassword();
                                if (password.length == 0) {
                                	passwordField.setText("Password");
                                	passwordField.setEchoChar((char) 0);
                                }
                            }
                        });
                
                        // Listener for the toggle button
                        toggleButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (toggleButton.isSelected()) {
                                	passwordField.setEchoChar((char) 0);
                                    toggleButton.setText("Hide");
                                } else {
                                	passwordField.setEchoChar('*');
                                    toggleButton.setText("Show");
                                }
                            }
                        });
    }
    
    
    
}
