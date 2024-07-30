package logic;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;


	private String Restaurants;
	private int Order_number;
	private double Total_price;
	private int Order_list_number;
	private String Order_adress;
	
	
	
	public Order(String restaurants, int order_number, double total_price, int order_list_number, String order_adress) {
		super();
		Restaurants = restaurants;
		Order_number = order_number;
		Total_price = total_price;
		Order_list_number = order_list_number;
		Order_adress = order_adress;
	}
	
	public String getRestaurants() {
		return Restaurants;
	}
	public void setRestaurants(String restaurants) {
		Restaurants = restaurants;
	}
	public int getOrder_number() {
		return Order_number;
	}
	public void setOrder_number(int order_number) {
		Order_number = order_number;
	}
	public double getTotal_price() {
		return Total_price;
	}
	public void setTotal_price(double total_price) {
		Total_price = total_price;
	}
	public int getOrder_list_number() {
		return Order_list_number;
	}
	public void setOrder_list_number(int order_list_number) {
		Order_list_number = order_list_number;
	}
	public String getOrder_adress() {
		return Order_adress;
	}
	public void setOrder_adress(String order_adress) {
		Order_adress = order_adress;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Order_number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Order_number == other.Order_number;
	}
	@Override
	public String toString() {
		return "Order [Restaurants=" + Restaurants + ", Order_number=" + Order_number + ", Total_price=" + Total_price
				+ ", Order_list_number=" + Order_list_number + ", Order_adress=" + Order_adress + "]";
	}
	
	

}
