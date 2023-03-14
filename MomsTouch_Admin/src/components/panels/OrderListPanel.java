package components.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import dao.OrderDAO;
import model.OrderInfo;


public class OrderListPanel extends JPanel {
	
	public OrderListPanel() {

		// order의 리턴값을 담는과정
		OrderInfo[] orderInfo = OrderDAO.getOrderList();

		JLabel order_history = new JLabel("주문 내역");
		order_history.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		order_history.setHorizontalAlignment(SwingConstants.CENTER);
		order_history.setBounds(260, 40, 236, 53);

		JLabel total_sales = new JLabel("총 매출액 : ");
		total_sales.setHorizontalAlignment(SwingConstants.CENTER);
		total_sales.setFont(new Font("굴림", Font.PLAIN, 15));
		total_sales.setBounds(480, 540, 109, 21);
	
		add(order_history);
		add(total_sales);
		
		setLayout(null);
		setBounds(200, 50, 800, 715);
		setBackground(new Color(250, 190, 105));
		setVisible(true);
	}

}
