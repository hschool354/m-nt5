package newproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class Warehouse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Warehouse() {
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
		contentPane.setLayout(null);
		
		Color customColor1 = new Color(6,118,85);
		
		
		// Bảng điều khiển
		JLayeredPane layeredHome = new JLayeredPane();
		layeredHome.setBackground(customColor1);
		layeredHome.setBounds(0, 0, 150, 518);
		contentPane.add(layeredHome);
		
		
		// cái layer bên trái
		JPanel rightpanel = new JPanel(); 
		rightpanel.setBounds(0, 0, 150, 518);
		rightpanel.setBackground(customColor1); 
		layeredHome.add(rightpanel, JLayeredPane.DEFAULT_LAYER);
		
		
		// logo đại diện
		JLabel lblNewLabel_4 = new JLabel("");
		ImageIcon icon2 = new ImageIcon("D:\\image\\logosb.png");
		Image image2 = icon2.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lblNewLabel_4.setIcon(new ImageIcon(image2));
		lblNewLabel_4.setBounds(30, 10, 80, 80); // Sửa kích thước label
		layeredHome.add(lblNewLabel_4, JLayeredPane.DRAG_LAYER);
		
		
		// logo nút home
		JLabel lblhome = new JLabel("");
		ImageIcon icon = new ImageIcon("D:\\image\\house.png");
		Image image = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblhome.setIcon(new ImageIcon(image));
		lblhome.setBounds(15, 120, 21, 19); // Sửa kích thước label
		layeredHome.add(lblhome, JLayeredPane.DRAG_LAYER);
		
		// Nút home
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				Home home = new Home();
				home.setVisible(true);
			}
		});
		btnHome.setBackground(new Color(255, 255, 255));
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHome.setBounds(10, 112, 115, 36);
		layeredHome.add(btnHome, JLayeredPane.DRAG_LAYER);
		
		// logo menu
		JLabel lblMenu = new JLabel("");
		ImageIcon icon1 = new ImageIcon("D:\\image\\menu.png");
		Image image1 = icon1.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblMenu.setIcon(new ImageIcon(image1));
		lblMenu.setBounds(15, 171, 21, 19); // Sửa kích thước label
		layeredHome.add(lblMenu, JLayeredPane.DRAG_LAYER);
		
		// nút Menu
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		btnMenu.setBackground(new Color(255, 255, 255));
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenu.setBounds(10, 163, 115, 36);
		layeredHome.add(btnMenu, JLayeredPane.DRAG_LAYER);
		
		// logo nhân sự
		JLabel lblPer = new JLabel("");
		ImageIcon icon3 = new ImageIcon("D:\\image\\human.png");
		Image image3 = icon3.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblPer.setIcon(new ImageIcon(image3));
		lblPer.setBounds(15, 222, 21, 19); // Sửa kích thước label
		layeredHome.add(lblPer, JLayeredPane.DRAG_LAYER);
		
		JButton btnPersonnel = new JButton("Personnel");
        btnPersonnel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String maNhanVien = DangNhap.layMaNhanVien();
		        if (maNhanVien == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi truy cập nhân sự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (!Check.kiemTraChucVu(maNhanVien)) {
		            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập phần nhân sự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        setVisible(false);
		        Personnel personnel = new Personnel();
		        personnel.setVisible(true);
            }
        });
        btnPersonnel.setBackground(new Color(255, 255, 255));
        btnPersonnel.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnPersonnel.setBounds(10, 214, 115, 36);
        layeredHome.add(btnPersonnel, JLayeredPane.DRAG_LAYER);
        
        // logo kho nguyên liệu
        JLabel lblNguyenlieu = new JLabel("");
		ImageIcon icon4 = new ImageIcon("D:\\image\\Nl.png");
		Image image4 = icon4.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblNguyenlieu.setIcon(new ImageIcon(image4));
		lblNguyenlieu.setBounds(15, 273, 21, 19); // Sửa kích thước label
		layeredHome.add(lblNguyenlieu, JLayeredPane.DRAG_LAYER);
		
		// logo nút kho nguyên liệu
		JButton btnNL = new JButton("Warehouse");
		btnNL.setBackground(new Color(255, 255, 255));
		btnNL.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNL.setBounds(10, 265, 115, 36);
		layeredHome.add(btnNL, JLayeredPane.DRAG_LAYER);
		
		// bảng nguyên liệu 
		JTable perTable = new JTable();
		perTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

		// Thanh cuộn cho bảng nguyên liệu 
		JScrollPane scrollPane = new JScrollPane(perTable);
		scrollPane.setBounds(160, 11, 916, 358);
		contentPane.add(scrollPane);
		
		// BẢNG dưới
		JLayeredPane layeredown = new JLayeredPane();
		layeredown.setBackground(customColor1);
		layeredown.setBounds(160, 380, 916, 138);
		contentPane.add(layeredown);
		
		// icon công thức
		JLabel lblNewLabel_rec = new JLabel("  ");
	    ImageIcon iconrec = new ImageIcon("D:\\image\\recipe.png");
		Image imagerec = iconrec.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblNewLabel_rec.setIcon(new ImageIcon(imagerec));
		lblNewLabel_rec.setBounds(16, 15, 20, 30);
		layeredown.add(lblNewLabel_rec, JLayeredPane.DRAG_LAYER);
		
		
		// nút xem công thức 
		JButton btnNewButtonReC = new JButton("XEM CÔNG THỨC");
	    btnNewButtonReC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				RecipeM recipeM = new RecipeM();
				recipeM.setVisible(true);
			}
		});
	    btnNewButtonReC.setBackground(new Color(255, 255, 255));
	    btnNewButtonReC.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnNewButtonReC.setBounds(10, 10, 150, 43);
	    layeredown.add(btnNewButtonReC, JLayeredPane.DRAG_LAYER);
	    
	    
	    // icon thêm nguyên liệu
	    JLabel lblNewLabel_add = new JLabel("  ");
		ImageIcon iconadd = new ImageIcon("D:\\image\\add.png");
		Image imageadd = iconadd.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblNewLabel_add.setIcon(new ImageIcon(imageadd));
		lblNewLabel_add.setBounds(16, 75, 20, 30);
		layeredown.add(lblNewLabel_add, JLayeredPane.DRAG_LAYER);
		
		
		//nút add
	    JButton btnNewButtonAdd = new JButton("THÊM CÔNG THỨC");
	    btnNewButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				Add add = new Add();
				add.setVisible(true);
			}
		});
	    btnNewButtonAdd.setBackground(new Color(255, 255, 255));
	    btnNewButtonAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnNewButtonAdd.setBounds(10, 70, 150, 43);
	    layeredown.add(btnNewButtonAdd, JLayeredPane.DRAG_LAYER);
		
		// Lấy dữ liệu từ cơ sở dữ liệu và hiển thị trên bảng
		try {
			Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
		    String query = "SELECT * FROM NguyenLieu";
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);

		    // Tạo model cho bảng hóa đơn
		    NonEditableTableModel IngredientModel = new NonEditableTableModel();
		    ResultSetMetaData metaData = resultSet.getMetaData();
		    int columnCount = metaData.getColumnCount();
		    
		    // Thêm tên cột vào model
		    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		    	IngredientModel.addColumn(metaData.getColumnName(columnIndex));
		    }
		    
		    // Thêm dữ liệu từ ResultSet vào model
		    while (resultSet.next()) {
		        Object[] rowData = new Object[columnCount];
		        for (int i = 0; i < columnCount; i++) {
		            rowData[i] = resultSet.getObject(i + 1);
		        }
		        IngredientModel.addRow(rowData);
		    }
		    
		    // Đặt model cho bảng hóa đơn
		    perTable.setModel(IngredientModel);

		    // Đóng kết nối
		    resultSet.close();
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

}
