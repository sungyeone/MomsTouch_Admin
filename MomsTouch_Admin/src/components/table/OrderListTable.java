package components.table;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.OrderDAO;
import model.OrderInfo;

public class OrderListTable extends JTable {
	
	static String[] header = {"주문 번호" , "종류", "상품명", "수량", "결제액", "주문 날짜"};
	
	static int rowCount = OrderDAO.getOrderListCount();
	
	static Object[][] table_contents = new Object[rowCount][header.length];
	
	public OrderListTable() {
		
		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		OrderInfo[] infos = OrderDAO.getOrderList();
		for(int i = 0; i < rowCount; i++) {
			table_contents[i][0] = infos[i].getMain_order_code();
			table_contents[i][1] = infos[i].getMenu_type();
			table_contents[i][2] = infos[i].getMenu_name();
			table_contents[i][3] = infos[i].getMenu_quantity();
			table_contents[i][4] = infos[i].getOrder_amount();
			table_contents[i][5] = infos[i].getOrder_time();
			model.addRow(table_contents[i]);
		}
		
		isCellEditable(rowCount, header.length);
		setModel(model);
		setTableSize();
		tableCellCenter();
		setRowHeight(30);
		setFont(new Font("굴림", Font.PLAIN, 15));
		getTableHeader().setReorderingAllowed(false);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	// "매장 코드", "주문 코드", "상품주문코드", "상품명", "수량", "비용", "주문시간"
	private void setTableSize() {
		getColumn("주문 번호").setPreferredWidth(50);
		getColumn("상품명").setPreferredWidth(150);
		getColumn("종류").setPreferredWidth(50);
		getColumn("수량").setPreferredWidth(50);
		getColumn("결제액").setPreferredWidth(80);
		getColumn("주문 날짜").setPreferredWidth(150);
	}
	
	private void tableCellCenter() {
	       // 테이블 내용 가운데 정렬하기
	         DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	         dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
	         
	         TableColumnModel tcm = getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
	         
	         for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	             tcm.getColumn(i).setCellRenderer(dtcr);
	             // 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
	             // 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
	         }
	}
		
}
