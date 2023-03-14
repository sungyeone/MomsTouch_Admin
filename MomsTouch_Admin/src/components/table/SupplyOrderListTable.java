package components.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.SupplyOrderDAO;
import dao.OjdbcConnection;
import model.SupplyModel;

public class SupplyOrderListTable extends JTable {
	
	JTable t = this;
	
	static String[] header = {"주문 번호", "발주품", "수량", "비용", "발주처", "발주날짜"};
	
	static int rowCount = SupplyOrderDAO.getIngredientOrderListCount();
	
	static Object[][] table_contents = new Object[rowCount][header.length];
	
	static DefaultTableModel model = new DefaultTableModel(header, 0);
	
	static SupplyModel[] s_model = SupplyOrderDAO.getIngredientOrderList();
	public SupplyOrderListTable() {
		for(int i = 0; i < rowCount; i++) {
				table_contents[i][0] = String.valueOf(s_model[i].getSupply_order_code());
				table_contents[i][1] = s_model[i].getSupply_name();
				table_contents[i][2] = String.valueOf(s_model[i].getSupply_qty());
				table_contents[i][3] = String.valueOf(s_model[i].getSupply_order_price());
				table_contents[i][4] = s_model[i].getSupplier_name();
				table_contents[i][5] = s_model[i].getSupply_order_date().substring(0, 10);
				model.addRow(table_contents[i]);
		}
		isCellEditable(rowCount, header.length);
		setModel(model);
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setColumnSize();
		tableCellCenter();
		getTableHeader().setReorderingAllowed(false);
	}
	
	// 내용 수정 불가 메소드
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	// 테이블 컬럼 사이즈 설정
	private void setColumnSize() {
		getColumn("주문 번호").setPreferredWidth(40);
		getColumn("발주품").setPreferredWidth(120);
		getColumn("수량").setPreferredWidth(30);
		getColumn("비용").setPreferredWidth(50);
		getColumn("발주처").setPreferredWidth(120);
		getColumn("발주날짜").setPreferredWidth(120);
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
	
	public static void removeTableRow() {
		model.setRowCount(0);
	}
	
	public static void refreshTable() {
		rowCount = SupplyOrderDAO.getIngredientOrderListCount();
		table_contents = new Object[rowCount][header.length];
		SupplyModel[] s_model = SupplyOrderDAO.getIngredientOrderList();
		for(int i = 0; i < rowCount; i++) {
			table_contents[i][0] = String.valueOf(s_model[i].getSupply_order_code());
			table_contents[i][1] = s_model[i].getSupply_name();
			table_contents[i][2] = String.valueOf(s_model[i].getSupply_qty());
			table_contents[i][3] = String.valueOf(s_model[i].getSupply_order_price());
			table_contents[i][4] = s_model[i].getSupplier_name();
			table_contents[i][5] = s_model[i].getSupply_order_date().substring(0, 10);
			model.addRow(table_contents[i]);
		}
	}
	
}
