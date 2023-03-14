package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.OrderInfo;

public class OrderDAO {

	public static OrderInfo[] getOrderList() {
		
		// 조인문자열
		String sql = "SELECT * FROM menu_order INNER JOIN main_order USING(main_order_number) ORDER BY main_order_number";
		// 행을 세줄 문자열
		String countOrderList = "SELECT count(*) FROM menu_order";
		//변수선언
		int numberOfOrderList = 0; 
		//배열하나에 오더인포를 다담는과정
		OrderInfo[] orderInfo = null;
		
		//실행한것을 rs,rs2에 담는과정
		try(
			// 준비하고	
			Connection conn = OjdbcConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(countOrderList);
			// 실행과정
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
				
		){
			//실행시킨 쿼리에서 행개수 뽑기
			while(rs2.next()) {
				numberOfOrderList = rs2.getInt("count(*)");
			}
			//행개수를 배열의 크기로 정함
			orderInfo = new OrderInfo[numberOfOrderList];
			//변수선언
			int i = 0;
			// i는 배열의 인덱스, 인덱스i에 상품명과 금액을 넣음
			while(rs.next()) {
				String product_type = null;
				int product_code;
				product_code = Integer.parseInt(rs.getString("menu_type"));
				if(product_code < 200) {
					product_type = "버거";
				} else if (product_code >= 200 && product_code < 400) {
					product_type = "치킨";
				} else if (product_code >= 400 && product_code < 500) {
					product_type = "신메뉴";
				} else if (product_code >= 500 && product_code < 600) {
					product_type = "사이드";
				}  else if (product_code >= 600 && product_code < 700) {
					product_type = "음료";
				}  else if (product_code >= 700 && product_code < 800) {
					product_type = "맘스세트";
				} 
				orderInfo[i] = new OrderInfo();
				orderInfo[i].setMain_order_code(rs.getInt("main_order_number"));
				orderInfo[i].setMenu_order_code(rs.getInt("menu_order_number"));
				orderInfo[i].setMenu_type(product_type);
				orderInfo[i].setMenu_name(rs.getString("menu_name"));
				orderInfo[i].setMenu_quantity(rs.getInt("menu_count"));
				orderInfo[i].setOrder_amount(rs.getInt("menu_order_amount"));
				orderInfo[i].setOrder_total_amount(rs.getInt("order_amount"));
				orderInfo[i].setOrder_time(rs.getString("order_time"));
				i++;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		//메서드를 실행시켜서 orderInfo값을 뻄
		return orderInfo;
		
	}
	
	public static int getOrderListCount() {
		
		String countOrderList = "SELECT count(*) FROM menu_order";
		
		int numberOfOrderList = 0; 
		
		try(
			// 준비하고	
			Connection conn = OjdbcConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(countOrderList);
			ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				numberOfOrderList = rs.getInt("count(*)");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return numberOfOrderList;
	}
	
	public static int getSpecificOrderTotalAmount(int main_order_num) {
		
		String sql = "SELECT menu_order_amount FROM menu_order WHERE main_order_number = ?";
		
		int order_total_amount = 0;
		try(Connection conn = OjdbcConnection.getConnection();
			  PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, main_order_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order_total_amount += rs.getInt("menu_order_amount");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return order_total_amount;
	}
	
	public static int getTotalAmount() {
		
		String sql = "SELECT order_amount FROM main_order";
		
		int order_total_amount = 0;
		try(Connection conn = OjdbcConnection.getConnection();
			  PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				order_total_amount += rs.getInt("order_amount");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return order_total_amount;
	}

}
