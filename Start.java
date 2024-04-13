package newproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import java.awt.TextField;
import java.awt.SystemColor;

import javax.swing.ImageIcon;


public class Start extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Start() {
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

        contentPane.setBackground(new Color(6, 118, 85));
        contentPane.setForeground(new Color(113, 170, 117));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon icon = new ImageIcon("D:\\image\\logosb.png");
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblNewLabel_1.setIcon(new ImageIcon(image));
        lblNewLabel_1.setBounds(57, 53, 209, 200);
        contentPane.add(lblNewLabel_1);
        
        RoundButton btnNewButton = new RoundButton("START");
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DangNhap frame = new DangNhap();
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(73, 399, 170, 35);
        contentPane.add(btnNewButton);
		
	}
}


