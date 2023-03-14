package frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import components.labels.HelloUser_Label;
import components.labels.ProfileImage_Label;
import components.panels.OrderListPanel;
import components.panels.SupplyOrderPanel;
import components.table.OrderListTable;
import components.table.SupplyOrderListTable;
import dao.OrderDAO;
import dao.SupplyOrderDAO;
import model.SupplyModel;

public class AdminPage extends JFrame {
	
	JFrame parent = this;
	
	JPanel func_list_1_panel = new OrderListPanel();
	JPanel func_list_2_panel = new SupplyOrderPanel();
	
	JLabel logo = new JLabel() {
		{

			setBounds(370, 5, 130, 39);
			
			try {
				BufferedImage bufferedImage = ImageIO.read(new File("images/로고2.png"));
				Image scaledImage = bufferedImage.getScaledInstance(130, 39, Image.SCALE_DEFAULT);
				setIcon(new ImageIcon(scaledImage));

			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	};
	
	JLabel logo2 = new JLabel() {
		{

			setBounds(510, 5, 130, 39);
			
			try {
				BufferedImage bufferedImage = ImageIO.read(new File("images/로고3-2.png"));
				Image scaledImage = bufferedImage.getScaledInstance(130, 39, Image.SCALE_DEFAULT);
				setIcon(new ImageIcon(scaledImage));

			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	};

	
	JLabel leftLabel = new JLabel() {
		{
			
			setBounds(0, 0, 200, 800);
			
			try {
				BufferedImage bufferedImage = ImageIO.read(new File("images/바탕.png"));
				Image scaledImage = bufferedImage.getScaledInstance(200, 800, Image.SCALE_DEFAULT);
				setIcon(new ImageIcon(scaledImage));

			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	};
	
	JLabel profile_image = new ProfileImage_Label(parent);
	JLabel hello_user;
	JLabel title_bar = new JLabel("") {
		{
			setOpaque(true);
			setBackground(new Color(70, 50, 41));
			setBounds(0,0, 984, 50);
		}
	};
	
	JTextField order_code_field = new JTextField() {
		{
			setBounds(480, 80, 30, 30);
			setHorizontalAlignment(JTextField.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 18));
		}
	};
	
	JLabel order_code_lbl = new JLabel("번 주문 총 결제액") {
		{
			setBounds(510, 80, 100, 30);
			setHorizontalAlignment(JTextField.CENTER);
		}
	};
	
	JTextField order_total_amount_field  = new JTextField() {
		{
			setBounds(620, 80, 100, 30);
    		setHorizontalAlignment(JTextField.CENTER);
    		setFont(new Font("굴림", Font.PLAIN, 18));
    		setEditable(false);
		}
	};
	
	
	JTable order_list_table = new OrderListTable() {
		{
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = getSelectedRow();
					int order_num = Integer.parseInt(String.valueOf(getValueAt(row, 0)));
					order_code_field.setText(String.valueOf(order_num));
					order_total_amount_field.setText(String.valueOf(OrderDAO.getSpecificOrderTotalAmount(order_num)));
				}
			});
		}
	};
    JScrollPane order_table_pane = new JScrollPane(order_list_table) {
    	{
    		setBounds(270, 170, 650, 400);
    		setBackground(Color.LIGHT_GRAY);
    		Border border1 = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    		setBorder(border1);
    	}
    };
    
    JTextField total_sales_amount = new JTextField() {
    	{
    		// amount의 값을 count에 i만큼 담는과정
    		int count = OrderDAO.getTotalAmount();
    		setOpaque(true);
    		setHorizontalAlignment(SwingConstants.CENTER);
    		setText("" + count);// 문자를 숫자로 변환할떄 parseInt
    		setFont(new Font("굴림", Font.PLAIN, 18));
    		setBounds(580, 530, 140, 30);
    		setColumns(10);
    		setEditable(false);
    	}
    };
    
    // 주문 취소용 텍스트 필드
    JTextField order_num_field = new JTextField() {
    	{
    		setBounds(595, 300, 30, 30);
    		setHorizontalAlignment(JTextField.CENTER);
    	}
    };
    
	JTable supply_order_list_table = new SupplyOrderListTable() {
		{
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = getSelectedRow();
					Object order_num = getValueAt(row, 0);
					order_num_field.setText(String.valueOf(order_num));
				}
			});
		}
	};
	
	JScrollPane supply_order_table_pane = new JScrollPane(supply_order_list_table) {
		{
			setBounds(270, 390, 650, 300);
			setVisible(false);
		}
	};
	
	String[] suppliers = {"본사", "대한청과유통", "치킨은역시가슴살유통", "AmericanGrocery유통"};
	JComboBox<String> supplier_name_cb = new JComboBox<>(suppliers) {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(190, 90, 152, 36);
		}
	};
	
	String[] hq_ingredients = {"햄버거 번 (pack)","닭가슴살 패티 (pack)", "쉬림프 패티 (pack)", "소고기 패티 (pack)",
			"미트볼 (1kg)", "케이준양념감자 (pack)", "양념소스 (2kg)", "간장소스 (2kg)"};
	String[] vnf_ingredients = {"양파 (1kg)", "토마토 (1kg)", "양상추 (1kg)"};
	String[] cbb_ingredients = {"손질된 닭 10호 (10마리)", "프리미엄 닭가슴살 패티 (pack)"};
	String[] amr_ingredients = {"피클 (3kg)", "케첩 (1kg)", "머스타드 소스 (1kg)", "체다치즈 (500g)", "모짜렐라 치즈 (1kg)"};
	int[] hq_ingredients_price = {2000, 8000, 6000, 10000, 5000, 8000, 10000, 10000};
	int[] vnf_ingredients_price = {2500, 7000, 8000};
	int[] cbb_ingredients_price = {12000, 11000};
	int[] amr_ingredients_price = {6000, 3000, 5000, 6000, 10000};
	
	static String[] selected_ingredients = {"없음"};
	static int[] selected_ingredients_price = {0};
	JComboBox<String> supply_name_cb = new JComboBox<>(selected_ingredients) {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(190, 150, 152, 36);
		}
	};
	{
		supplier_name_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supply_name_cb.removeAllItems();
				if(String.valueOf(supplier_name_cb.getSelectedItem()).equals(suppliers[0])) {
					selected_ingredients = hq_ingredients;
					selected_ingredients_price = hq_ingredients_price;
				} else if (String.valueOf(supplier_name_cb.getSelectedItem()).equals(suppliers[1])) {
					selected_ingredients = vnf_ingredients;
					selected_ingredients_price = vnf_ingredients_price;
				} else if(String.valueOf(supplier_name_cb.getSelectedItem()).equals(suppliers[2])) {
					selected_ingredients = cbb_ingredients;
					selected_ingredients_price = cbb_ingredients_price;
				} else if (String.valueOf(supplier_name_cb.getSelectedItem()).equals(suppliers[3])) {
					selected_ingredients = amr_ingredients;
					selected_ingredients_price = amr_ingredients_price;
				}
				for(int i = 0; i < selected_ingredients.length; i++) {
					supply_name_cb.addItem(selected_ingredients[i]);
				}
			}
		});
	}
	
	static List<Integer> nums = new ArrayList<>();
	{
		for(int i = 1; i <= 100; i++) {
			nums.add(i);
		}
	}
	
	SpinnerListModel list_model = new SpinnerListModel(nums);
	JSpinner supply_count_spin = new JSpinner(list_model) {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(450, 90, 73, 36);
		}
	};
	
	JTextField order_cost_field = new JTextField() {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(450, 150, 152, 36);
			setColumns(10);
			setText("￦0");
		}
	};
	
	{
		supply_name_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0 ; i < selected_ingredients.length; i++) {
					if(String.valueOf(supply_name_cb.getSelectedItem()).equals(selected_ingredients[i])) {
						String order_cost = "￦"+ String.valueOf(selected_ingredients_price[i]);
						order_cost_field.setText(order_cost);
						supply_count_spin.setValue(nums.get(0));
					}
				}
			}
		});
		supply_count_spin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int supply_count = Integer.parseInt(String.valueOf(supply_count_spin.getValue()));
				for(int i = 0; i < selected_ingredients.length; i++) {
					if(String.valueOf(supply_name_cb.getSelectedItem()).equals(selected_ingredients[i]) && supply_count > 0) {
						String order_cost = "￦"+ String.valueOf(selected_ingredients_price[i] * supply_count);
						order_cost_field.setText(order_cost);
					}
				}
			}
		});
	}
	
	Calendar calendar = Calendar.getInstance();
	Date complicateToday = calendar.getTime();
	SimpleDateFormat simplify = new SimpleDateFormat("YYYY-MM-dd");
	String today = simplify.format(complicateToday);
	JTextField order_date_field = new JTextField() {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(190, 210, 152, 36);
			setText(today);
			setEditable(false);
			setColumns(10);
		}
	};
	
	JTextField ordered_person_name_field = new JTextField() {
		{
			setFont(new Font("굴림", Font.PLAIN, 18));
			setBounds(450, 210, 152, 36);
			setEditable(false);
			setText("관리자");
			setColumns(10);
		}
	};
	
	JTextField total_spent_amount_field = new JTextField() {
		{
			setBounds(590, 650, 130, 35);
			setText(String.valueOf(SupplyOrderDAO.getTotalSpentAmount()));
			setFont(new Font("굴림", Font.PLAIN, 20));
			setHorizontalAlignment(JTextField.CENTER);
			setEditable(false);
		}
	};
	
	JButton order_button = new JButton() {
		{
			setFont(new Font("굴림", Font.BOLD, 15));
			setBounds(520, 30, 80, 35);
			setForeground(Color.white);
			setBackground(new Color(70, 50, 41));
			Border border = BorderFactory.createLineBorder(new Color(70, 50, 41));
			setBorder(border);
			setText("발주");
			setFocusable(false);
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SupplyModel model = new SupplyModel();
					model.setStore_code(1);
					model.setSupplier_name(String.valueOf(supplier_name_cb.getSelectedItem()));
					model.setSupply_name(String.valueOf(supply_name_cb.getSelectedItem()));
					model.setSupply_qty(Integer.parseInt(String.valueOf(supply_count_spin.getValue())));
					model.setSupply_order_price(Integer.parseInt(order_cost_field.getText().substring(1)));
					model.setSupply_order_date(order_date_field.getText());
					if(model.getSupply_name().equals("없음")) {
						JOptionPane.showMessageDialog(null, "발주처와 발주품을 선택하세요", "오류", JOptionPane.ERROR_MESSAGE);
					} else {
						SupplyOrderDAO.InsertIngredientOrder(model);
						SupplyOrderListTable.removeTableRow();
						SupplyOrderListTable.refreshTable();
						total_spent_amount_field.setText(String.valueOf(SupplyOrderDAO.getTotalSpentAmount()));
					}
				}
			});
		}
	};
	
	JButton cancel_order_button = new JButton("발주 취소") {
		{
			setBounds(640, 300, 80, 30);
			setForeground(Color.white);
			setBackground(new Color(70, 50, 41));
			Border border = BorderFactory.createLineBorder(new Color(70, 50, 41));
			setBorder(border);
			setFocusable(false);
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SupplyOrderDAO.cancelSelectedIngredientOrder(order_num_field.getText());
					SupplyOrderListTable.removeTableRow();
					SupplyOrderListTable.refreshTable();
					total_spent_amount_field.setText(String.valueOf(SupplyOrderDAO.getTotalSpentAmount()));
				}
			});
		}
	};
	JLabel func_list_1_image = new JLabel() {
		{
			setBounds(25, 155, 50, 40);
			
			try {
				BufferedImage bufferedImage = ImageIO.read(new File("images/주문내역.png"));
				Image scaledImage = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				setIcon(new ImageIcon(scaledImage));

			} catch (IOException e2) {
				e2.printStackTrace();
			}	
		}
	};
	JLabel func_list_1 = new JLabel() {
		{
			setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
			setForeground(Color.white);
			setHorizontalAlignment(SwingConstants.RIGHT);
			setBounds(25, 150, 166, 60);
			setOpaque(true);
			setBackground(new Color(193, 118, 67));
			setText("주문 내역 확인");
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					func_list_2_panel.setVisible(false);
					func_list_1_panel.setVisible(true);
					supply_order_table_pane.setVisible(false);
					order_table_pane.setVisible(true);
				}
			});
		}
	};
	JLabel func_list_2_image = new JLabel() {
		{
			setBounds(30, 225, 50, 50);
			
			try {
				BufferedImage bufferedImage = ImageIO.read(new File("images/발주내역.png"));
				Image scaledImage = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				setIcon(new ImageIcon(scaledImage));

			} catch (IOException e2) {
				e2.printStackTrace();
			}	
		}
	};
	
	JLabel func_list_2 = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
			setForeground(Color.white);
			setOpaque(true);
			setBounds(25, 220, 166, 60);
			setBackground(new Color(193, 118, 67));
			setText("발주");
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					func_list_1_panel.setVisible(false);
					func_list_2_panel.setVisible(true);
					order_table_pane.setVisible(false);
					supply_order_table_pane.setVisible(true);
				}
			});
		}
	};
	JLabel func_list_bg = new JLabel() {
		{
			setOpaque(true);
			setBackground(new Color(70, 50, 41));
			setBounds(25, 81, 166, 124);
		}
	};
	
	public AdminPage(String position) {
		
		hello_user = new HelloUser_Label(position);
		
		add(logo);
		add(logo2);
		add(profile_image);
		add(hello_user);
		add(title_bar);
		add(func_list_1_image);
		add(func_list_2_image);
		add(func_list_1);
		add(func_list_2);
//		add(func_list_bg);
		add(order_table_pane);
		add(func_list_1_panel);
		func_list_1_panel.add(order_code_field);
		func_list_1_panel.add(order_code_lbl);
		func_list_1_panel.add(order_total_amount_field);
		func_list_1_panel.add(total_sales_amount);
		add(supply_order_table_pane);
		add(func_list_2_panel);
		func_list_2_panel.add(supplier_name_cb);
		func_list_2_panel.add(supply_name_cb);
		func_list_2_panel.add(supply_count_spin);
		func_list_2_panel.add(order_cost_field);
		func_list_2_panel.add(order_date_field);
		func_list_2_panel.add(ordered_person_name_field);
		func_list_2_panel.add(order_button);
		func_list_2_panel.add(cancel_order_button);
		func_list_2_panel.add(order_num_field);
		func_list_2_panel.add(total_spent_amount_field);
		add(leftLabel);
		
		setLayout(null);
		setResizable(false);
		setBounds(100, 0, 1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
//		getContentPane().setBackground(Color.WHITE);
	}
}
