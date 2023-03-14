package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LoginDAO {

//	static boolean access;
	public static boolean getLoginAccess(String input_id, String input_pw) {
		
		String sql = "SELECT * FROM franchisee_store WHERE employee_id = ?";

		String id = null;
		String pw = null;
		try (Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			conn.commit();
			
			pstmt.setString(1, input_id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("employee_id");
				pw = rs.getString("employee_password");
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다.", "로그인 오류", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		if (input_id.equals(id) && input_pw.equals(pw)) {
			return true;
		} else {
		JOptionPane.showMessageDialog(null, "비밀번호가 잘못 입력되었습니다.",
				"로그인 오류", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static String getPosition(String input_id) {
		
		String sql = "SELECT employee_position FROM franchisee_store WHERE employee_id = ?";
		
		String position = null;
		try(Connection conn = OjdbcConnection.getConnection();
			  PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, input_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				position = rs.getString("employee_position");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return position;
	}
}
