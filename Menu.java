package newproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;




public class Menu extends JFrame {

    
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredoder;
	
	
	public class DrinkDAO {
	    public static Map<String, Double> getDrinkPrices() {
	        Map<String, Double> drinkPrices = new HashMap<>();

	        String query = "SELECT TENTU, DONGIA FROM ThucUong";
	        
	        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                String name = resultSet.getString("TENTU");
	                double price = resultSet.getDouble("DONGIA");
	                drinkPrices.put(name, price);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return drinkPrices;
	    }
	}
	
	private Map<String, Integer> billItems = new HashMap<>(); 
	private Map<String, Double> drinkPrices = new HashMap<>();
	
	public Menu() {

		drinkPrices = DrinkDAO.getDrinkPrices();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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

		// Màu
		Color customColor1 = new Color(6,118,85);// Màu xanh nhạt

		
		 // BẢNG ĐIỀU KHIỂN
		JLayeredPane layeredHome = new JLayeredPane();
		layeredHome.setBackground(customColor1);
		layeredHome.setBounds(0, 0, 150, 563);
		contentPane.add(layeredHome);
		
		JPanel rightpanel = new JPanel(); 
		rightpanel.setBounds(0, 0, 150, 563);
		rightpanel.setBackground(customColor1); 
		layeredHome.add(rightpanel, JLayeredPane.DEFAULT_LAYER);
		
		
		// logo dạid diện
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
		
		// logo nút menu
		JLabel lblMenu = new JLabel("");
		ImageIcon icon1 = new ImageIcon("D:\\image\\menu.png");
		Image image1 = icon1.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblMenu.setIcon(new ImageIcon(image1));
		lblMenu.setBounds(15, 171, 21, 19); // Sửa kích thước label
		layeredHome.add(lblMenu, JLayeredPane.DRAG_LAYER);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMenu.setBackground(new Color(255, 255, 255));
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenu.setBounds(10, 163, 115, 36);
		layeredHome.add(btnMenu, JLayeredPane.DRAG_LAYER);
		
		// LOGO NHÂN SỰ
		JLabel lblPer = new JLabel("");
		ImageIcon icon3 = new ImageIcon("D:\\image\\human.png");
		Image image3 = icon3.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblPer.setIcon(new ImageIcon(image3));
		lblPer.setBounds(15, 222, 21, 19); // Sửa kích thước label
		layeredHome.add(lblPer, JLayeredPane.DRAG_LAYER);
		
		/// Nút "Nhân sự"
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
		
		
		//logo kho nguyên liệu
		JLabel lblNguyenlieu = new JLabel("");
		ImageIcon icon4 = new ImageIcon("D:\\image\\Nl.png");
		Image image4 = icon4.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblNguyenlieu.setIcon(new ImageIcon(image4));
		lblNguyenlieu.setBounds(15, 273, 21, 19); // Sửa kích thước label
		layeredHome.add(lblNguyenlieu, JLayeredPane.DRAG_LAYER);
		
		
		// nút kho nguyên liệu
		JButton btnNL = new JButton("Warehouse");
		btnNL.addActionListener(new ActionListener() {
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
		        Warehouse warehouse = new Warehouse();
		        warehouse.setVisible(true);
		    }
		});
		btnNL.setBackground(new Color(255, 255, 255));
		btnNL.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNL.setBounds(10, 265, 115, 36);
		layeredHome.add(btnNL, JLayeredPane.DRAG_LAYER);
		
		// layer ở dưới
		JLayeredPane layeredDown = new JLayeredPane();
		layeredDown.setBounds(160, 501, 556, 62);
        contentPane.add(layeredDown);
        
        // icon setting
        JLabel lblSetting = new JLabel("");
        lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon icon5 = new ImageIcon("D:\\image\\settings.png");
		Image image5 = icon5.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		lblSetting.setIcon(new ImageIcon(image5));
		lblSetting.setBounds(16, 10, 41, 36); // Sửa kích thước label
		layeredDown.add(lblSetting, JLayeredPane.DRAG_LAYER);
		
		// nút setting
		JButton btnSet = new JButton("SETTINGS");
		btnSet.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        setVisible(false);
		        Settings settings = new Settings();
		        settings.setVisible(true);
		    }
		});
		btnSet.setBackground(new Color(255, 255, 255));
		btnSet.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSet.setBounds(10, 10, 119, 36);
		layeredDown.add(btnSet, JLayeredPane.DRAG_LAYER);
        
		// phần đặt món
		layeredoder = new JLayeredPane();
        layeredoder.setBackground(customColor1);
        layeredoder.setBounds(726, 82, 150, 353);
        contentPane.add(layeredoder);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(160, 11, 556, 479);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Panel cho nước CAFE
        JPanel drinksPanel = new JPanel();
        tabbedPane.addTab("COFFEE", null, drinksPanel, null);
        drinksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Cài đặt layout cho panel

        // Thêm các nút và spinner cho nước uống
        addDrinkItem(drinksPanel, "Black Coffeen", "D:\\image\\cafe.png");
        addDrinkItem(drinksPanel, "Cappuccino", "D:\\image\\capuchino1.png");
        addDrinkItem(drinksPanel, "Milk coffee", "D:\\image\\bx2.png");
        addDrinkItem(drinksPanel, "Latte", "D:\\image\\caramel.png");
        

        // Panel cho Trà
        JPanel teaPanel = new JPanel();
        tabbedPane.addTab("Tea", null, teaPanel, null);
        teaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Cài đặt layout cho panel

        // Thêm các nút và spinner cho thức ăn
        addDrinkItem(teaPanel, "Vietnamese lotus tea", "D:\\image\\ts1.png");
        addDrinkItem(teaPanel, "Peach tea", "D:\\image\\td1.png");
        addDrinkItem(teaPanel, "Lychee tea", "D:\\image\\tv2.png");
        addDrinkItem(teaPanel, "Green tea", "D:\\image\\tx.png");
        
        // Panel cho đá xay
        JPanel icePanel = new JPanel();
        tabbedPane.addTab("FREEZE", null, icePanel, null);
        icePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Cài đặt layout cho panel

        // Thêm các nút và spinner cho thức ăn
        addDrinkItem(icePanel, "Chocolate Ice Blended", "D:\\image\\daxaysocola.png");
        addDrinkItem(icePanel, "Cookies and cream", "D:\\image\\cookiecream.png");
        addDrinkItem(icePanel, "Caramel Phin Freeze", "D:\\image\\caramel.png");
        addDrinkItem(icePanel, "Classic Phin Freeze", "D:\\image\\classic-pHin.png");
        
        //Panel cho nước ép trái cây
        JPanel juicePanel = new JPanel();
        tabbedPane.addTab("JUICE", null, juicePanel, null);
        juicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Cài đặt layout cho panel

        // Thêm các nút và spinner cho thức ăn
        addDrinkItem(juicePanel, "Orange juice", "D:\\image\\cam.png");
        addDrinkItem(juicePanel, "Apple juice", "D:\\image\\nt1.png");
        addDrinkItem(juicePanel, "Carrot juice", "D:\\image\\carrot.png");
        addDrinkItem(juicePanel, "Grapefruit juice", "D:\\image\\Grapefruit.png");
        
        // Panel cho soft drinks 
        JPanel dinksPanel = new JPanel();
        tabbedPane.addTab("SOFT DRINKS ", null, dinksPanel, null);
        dinksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Cài đặt layout cho panel

        // Thêm các nút và spinner cho thức ăn
        addDrinkItem(dinksPanel, "Cocacola", "D:\\image\\coca.png");
        addDrinkItem(dinksPanel, "Sting", "D:\\image\\sting1.png");
        addDrinkItem(dinksPanel, "Redbull", "D:\\image\\redbull1.png");
        addDrinkItem(dinksPanel, "Pepsi", "D:\\image\\pepsi2.png");
        
        
        JLabel lblNewLabel = new JLabel("BILLS");
        lblNewLabel.setBounds(779, 24, 57, 25);
        contentPane.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 15));

        setVisible(true);
        
        //nút tính tiền 
        JButton calculateButton = new JButton("Tính tiền");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String maNhanVien = DangNhap.layMaNhanVien();
            	String maKhuVuc = Settings.selectedArea;
                if (maNhanVien == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi tính tiền.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(maKhuVuc == null ) {
                	JOptionPane.showMessageDialog(null, "Vui lòng vô cài đặt chọn khu vực trước khi tính tiền.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double total = calculateTotalBill();
                String maHoaDon = generateUniqueInvoiceID();
                insertInvoiceToDatabase(maHoaDon, maNhanVien, maKhuVuc, total);
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu vào cơ sở dữ liệu.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        calculateButton.setBounds(741, 465, 120, 25);
        contentPane.add(calculateButton);
        
		
	}
	
	
	// hàm thêm nuơcs uống
	private void addDrinkItem(JPanel panel, String drinkName, String imagePath) {
	    JPanel itemPanel = new JPanel(new BorderLayout());
	    ImageIcon icon = new ImageIcon(imagePath); 
	    JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
	    itemPanel.add(imageLabel, BorderLayout.NORTH);

	    JPanel detailsPanel = new JPanel(new GridLayout(0, 1));
	    JButton drinkButton = new JButton(drinkName);
	    
	    // Lấy giá từ cơ sở dữ liệu
	    Map<String, Double> drinkPrices = DrinkDAO.getDrinkPrices();
	    double price = drinkPrices.getOrDefault(drinkName, 0.0);
	    JLabel priceLabel = new JLabel("$" + price); // Hiển thị giá
	    detailsPanel.add(priceLabel);
	    
	    JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)); // Giá trị từ 0 đến 10
	    spinner.setName(drinkName); // Đặt tên của spinner là tên món
	    spinner.addChangeListener(e -> {
	        // Cập nhật số lượng món khi số lượng thay đổi
	        int quantity = (Integer) spinner.getValue();
	        if (quantity == 0) {
	            billItems.remove(drinkName); // Xóa món nếu số lượng về 0
	        } else {
	            billItems.put(drinkName, quantity);
	        }
	        updateBill(); // Cập nhật phần "BILLS"
	    });
	    detailsPanel.add(drinkButton);
	    detailsPanel.add(spinner);
	    itemPanel.add(detailsPanel, BorderLayout.CENTER);

	    panel.add(itemPanel);
	}
	
	
	//cập nhập số lượng món đã chọn
	private void addDrinkItemToBill(String drinkName, int quantity) {
	    double price = drinkPrices.getOrDefault(drinkName, 0.0); // Lấy giá của món từ map drinkPrices
	    double totalPrice = price * quantity;
	    
	    // Hiển thị món, số lượng và giá tiền trong phần bill
	    JLabel itemLabel = new JLabel(drinkName + " - $" + price + " x " + quantity + " = $" + totalPrice);
	    layeredoder.add(itemLabel);
	}


    // Phương thức cập nhật phần "BILLS"
    private void updateBill() {
        // Xóa tất cả các mục trước khi cập nhật
        layeredoder.removeAll();

        int yPos = 40; // Vị trí dọc của mỗi món trong phần "BILLS"

        // Duyệt qua danh sách các món đã chọn và cập nhật phần "BILLS"
        for (Map.Entry<String, Integer> entry : billItems.entrySet()) {
            JLabel itemNameLabel = new JLabel(entry.getKey());
            itemNameLabel.setBounds(10, yPos, 100, 25);
            layeredoder.add(itemNameLabel, JLayeredPane.DRAG_LAYER);

            JLabel itemQuantityLabel = new JLabel("x" + entry.getValue());
            itemQuantityLabel.setBounds(110, yPos, 50, 25);
            layeredoder.add(itemQuantityLabel, JLayeredPane.DRAG_LAYER);

            yPos += 25; // Tăng vị trí dọc cho mỗi món
        }
        
        updateBillTotal();
        layeredoder.revalidate();
        layeredoder.repaint();
    }
    
    
    // cập nhập giá tiền trong phần bill
    private void updateBillTotal() {
        double total = 0;
        for (String drinkName : billItems.keySet()) {
            int quantity = billItems.get(drinkName);
            double price = drinkPrices.getOrDefault(drinkName, 0.0);
            total += price * quantity;
        }
        
        // Xóa phần tổng tiền trước khi cập nhật
        Component[] components = layeredoder.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Total:")) {
                layeredoder.remove(component);
            }
        }

        // Hiển thị tổng tiền trong phần bill
        JLabel totalLabel = new JLabel("Total: $" + total);
        totalLabel.setBounds(10, 300, 100, 25); // Đặt vị trí hiển thị tổng tiền
        layeredoder.add(totalLabel, JLayeredPane.DRAG_LAYER);
    }
    
    private double calculateTotalBill() {
        double total = 0;
        for (String drinkName : billItems.keySet()) {
            int quantity = billItems.get(drinkName);
            double price = drinkPrices.getOrDefault(drinkName, 0.0);
            total += price * quantity;
        }
        return total;
    }
    
    
 // Phương thức để tạo mã hóa đơn mới không trùng lặp
    private String generateUniqueInvoiceID() {
        String latestID = "HD000"; // Mã hóa đơn mặc định
        String query = "SELECT MAX(MAHD) FROM HOADON"; // Truy vấn lấy mã hóa đơn lớn nhất từ cơ sở dữ liệu

        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String maxID = resultSet.getString(1); // Lấy mã hóa đơn lớn nhất từ kết quả truy vấn
                if (maxID != null) {
                    // Tách phần số từ mã hóa đơn lớn nhất
                    int num = Integer.parseInt(maxID.substring(2)) + 1;
                    latestID = String.format("HD%03d", num); // Format lại mã hóa đơn mới
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return latestID;
    }


    // Phương thức để chèn hóa đơn vào cơ sở dữ liệu
    private void insertInvoiceToDatabase(String maHoaDon, String maNhanVien,String maKhuVuc, double total) {
    	String query = "INSERT INTO HOADON (MAHD, MANV, MAKV, NGAYLAP, TONGTIEN) VALUES (?, ?, ?,GETDATE(), ?)";

        try (Connection connection = DriverManager.getConnection(ConnectDB.url, ConnectDB.user, ConnectDB.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, maHoaDon);
            preparedStatement.setString(2, maNhanVien);
            preparedStatement.setString(3, maKhuVuc);
            preparedStatement.setDouble(4, total);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Map<String, Integer> getBillItems() {
        return billItems;
    }
}
