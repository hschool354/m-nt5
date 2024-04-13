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
import java.awt.event.ActionEvent;

public class comingsoon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public comingsoon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 161);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tính năng này sẽ sớm cập nhật");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 27, 200, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				DangNhap Dangnhap = new DangNhap();
				Dangnhap.setVisible(true);
			}
		});
        btnNewButton.setBounds(71, 66, 96, 23);
        btnNewButton.setBackground(Color.BLACK); 
        btnNewButton.setForeground(SystemColor.textHighlightText);
        contentPane.add(btnNewButton);
	}

}