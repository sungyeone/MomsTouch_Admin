package components.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import components.table.SupplyOrderListTable;
import dao.SupplyOrderDAO;
import model.SupplyModel;

public class SupplyOrderPanel extends JPanel {

	JLabel title_1_lbl = new JLabel() {
		{
			setFont(new Font("HY헤드라인M", Font.BOLD, 25));
			setHorizontalAlignment(SwingConstants.CENTER);
			setBounds(280, 30, 180, 35);
			setText("발주");
		}
	};
	JLabel supplier_name_lbl = new JLabel() {
		{
			setFont(new Font("굴림", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);
			setBounds(100, 95, 86, 26);
			setText("발주처");
		}
	};
	
	JLabel supply_name_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 14));
			setBounds(100, 155, 86, 26);
			setText("발주품");
		}
	};
	
	JLabel supply_count_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 14));
			setBounds(360, 95, 86, 26);
			setText("수량");
		}
	};
	
	JLabel order_cost_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 14));
			setBounds(360, 155, 86, 26);
			setText("비용");
		}
	};
	
	JLabel order_date_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 14));
			setBounds(100, 215, 86, 26);
			setText("발주날짜");
		}
	};
	
	JLabel ordered_person_name_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("굴림", Font.PLAIN, 14));
			setBounds(360, 215, 86, 26);
			setText("발주자");
		}
	};
	
	JLabel title_2_lbl = new JLabel() {
		{
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("HY헤드라인M", Font.BOLD, 25));
			setBounds(280, 280, 180, 35);
			setText("발주 내역");
		}
	};
	
	JLabel order_num_lbl = new JLabel("주문번호") {
		{
			setBounds(530, 300, 60, 30);
		}
	};
	
	JLabel total_spent_amount = new JLabel("총 지출액") {
		{
			setBounds(510, 655, 70, 30);
			setFont(new Font("굴림", Font.PLAIN, 15));
		}
	};
	
	public SupplyOrderPanel() {
		
		add(title_1_lbl);
		add(supplier_name_lbl);
		add(supply_name_lbl);
		add(supply_count_lbl);
		add(order_cost_lbl);
		add(order_date_lbl);
		add(ordered_person_name_lbl);
		add(title_2_lbl);
		add(order_num_lbl);
		add(total_spent_amount);
		
		setLayout(null);
		setBounds(200, 50, 800, 715);
		setBackground(new Color(250, 190, 105));
		setVisible(false);
	}
}
