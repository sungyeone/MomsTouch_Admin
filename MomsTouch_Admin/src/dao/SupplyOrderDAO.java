package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.SupplyModel;

public class SupplyOrderDAO {

	public static void InsertIngredientOrder(SupplyModel model) {
		
		String sql = "INSERT INTO supply_order VALUES(?, supply_order_code_seq.nextval, ?, ?, ?, ?, ?)";
		
		try (Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, model.getStore_code());
			pstmt.setString(2, model.getSupplier_name());
			pstmt.setString(3, model.getSupply_name());
			pstmt.setInt(4, model.getSupply_order_price());
			pstmt.setInt(5, model.getSupply_qty());
			pstmt.setString(6, model.getSupply_order_date());
			
			pstmt.executeUpdate();
			
			conn.commit();
			JOptionPane.showMessageDialog(null, "발주 완료", "알림", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "발주 실패", "알림", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static SupplyModel[] getIngredientOrderList() {
		
		String order_count = "SELECT count(*) FROM supply_order";
		String orders = "SELECT * FROM supply_order ORDER BY supply_order_code";
		
		SupplyModel[] model_array = null;
		
		try (Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(order_count);
				PreparedStatement pstmt2 = conn.prepareStatement(orders);
				ResultSet rs = pstmt.executeQuery();) {
			
			conn.commit();
			
			int num_of_model = 0;
			while(rs.next()) {
				num_of_model = rs.getInt("count(*)");
			}
			model_array = new SupplyModel[num_of_model];
			
			for(int i = 0; i < num_of_model; i++) {
				model_array[i] = new SupplyModel();
			}
			
			ResultSet rs2 = pstmt2.executeQuery();
			
			int i = 0;
			SupplyModel model = new SupplyModel(); 
			SimpleDateFormat simplify = new SimpleDateFormat("yyyy-MM-dd");
			while(rs2.next()) {
				model_array[i].setStore_code(rs2.getInt("store_code"));
				model_array[i].setSupply_order_code(rs2.getInt("supply_order_code"));
				model_array[i].setSupplier_name(rs2.getString("supplier_name"));
				model_array[i].setSupply_name(rs2.getString("supply_name"));
				model_array[i].setSupply_order_price(rs2.getInt("supply_order_price"));
				model_array[i].setSupply_qty(rs2.getInt("supply_qty"));
				String date = simplify.format(rs2.getDate("supply_order_date"));				
				model_array[i].setSupply_order_date(date);
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model_array;
	}
	
	public static int getIngredientOrderListCount () {
		
		String order_count = "SELECT count(*) FROM supply_order";
		
		int list_count = 0;
		try (Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(order_count);) {
			conn.commit();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list_count = rs.getInt("count(*)");
			}
		} catch  (SQLException e) {
			e.printStackTrace();
		}
		
		return list_count;
	}
	
	public static void cancelSelectedIngredientOrder(Object order_num) {
		
		String cancel_order = "DELETE FROM supply_order WHERE supply_order_code = ?"; 
		String order_date = "SELECT supply_order_date FROM supply_order WHERE supply_order_code = ?";
		
		try (Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(cancel_order);
				PreparedStatement pstmt2 = conn.prepareStatement(order_date);) {
			
			pstmt2.setObject(1, order_num);
			
			ResultSet rs2 = pstmt2.executeQuery();
			int day = 0;
			int month = 0;
			int year = 0;
			SimpleDateFormat get_year = new SimpleDateFormat("yyyy");
			SimpleDateFormat get_month = new SimpleDateFormat("MM"); 
			SimpleDateFormat get_day = new SimpleDateFormat("dd");
			
			while(rs2.next()) {
				day = Integer.parseInt(get_day.format(rs2.getTimestamp("supply_order_date")));
				month = Integer.parseInt(get_month.format(rs2.getTimestamp("supply_order_date")));
				year = Integer.parseInt(get_year.format(rs2.getTimestamp("supply_order_date")));
			}
			
			Calendar calendar = Calendar.getInstance();
			Date complicateCurrentDate = calendar.getTime();
			SimpleDateFormat simplify_day = new SimpleDateFormat("dd");
			SimpleDateFormat simplify_month = new SimpleDateFormat("MM");
			SimpleDateFormat simplify_year = new SimpleDateFormat("yyyy");
			int today = Integer.parseInt(simplify_day.format(complicateCurrentDate));
			int this_month = Integer.parseInt(simplify_month.format(complicateCurrentDate));
			int this_year = Integer.parseInt(simplify_year.format(complicateCurrentDate));
			
			if((this_year - year) * 365 + (this_month - month) * 30 + (today - day) < 5) {
				pstmt.setObject(1, order_num);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "주문 취소 완료", "주문 취소", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "주문 취소 실패 : 5일이상 지난 주문", "주문 취소", JOptionPane.ERROR_MESSAGE);
			}
			conn.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "주문 취소 실패 : 오류", "주문 취소", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public static int getTotalSpentAmount() {
		
		String get_total_amount = "SELECT supply_order_price FROM supply_order";
		
		int total_order_amount = 0;
		try(Connection conn = OjdbcConnection.getConnection();
			  PreparedStatement pstmt = conn.prepareStatement(get_total_amount);
			  ResultSet rs = pstmt.executeQuery();) {
			
			List<Integer> total_amounts = new ArrayList<>();
			while(rs.next()) {
				total_amounts.add(rs.getInt("supply_order_price"));
			}
			for(int i = 0; i < total_amounts.size(); i++) {
				total_order_amount += total_amounts.get(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total_order_amount;
	}
}
