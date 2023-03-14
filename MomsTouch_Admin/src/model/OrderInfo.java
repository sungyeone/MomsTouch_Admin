package model;

public class OrderInfo {
	// 빼거나 비꿔주는 메서드
	private int menu_order_code;
	private int main_order_code;
	private String menu_type;
	private String menu_name;
	private int menu_quantity;
	private int order_amount;
	private int order_total_amount;
	private String order_time;
	
	public int getMenu_order_code() {
		return menu_order_code;
	}
	public void setMenu_order_code(int menu_order_code) {
		this.menu_order_code = menu_order_code;
	}
	public int getMain_order_code() {
		return main_order_code;
	}
	public void setMain_order_code(int main_order_code) {
		this.main_order_code = main_order_code;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_quantity() {
		return menu_quantity;
	}
	public void setMenu_quantity(int menu_quantity) {
		this.menu_quantity = menu_quantity;
	}
	public int getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}
	public int getOrder_total_amount() {
		return order_total_amount;
	}
	public void setOrder_total_amount(int order_total_amount) {
		this.order_total_amount = order_total_amount;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
}
