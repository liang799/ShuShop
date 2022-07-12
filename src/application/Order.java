package application;

import java.util.ArrayList;

public class Order {
	private Customer customer;
	private ArrayList<Book> orderItems;
	private double totalPrice;
	
	Order(Customer buyer, ArrayList<Book> books) {
		customer = buyer;
		orderItems = books;
	}
	
	public double getTotalPrice() {
		double bill = 0;
		for(Book item : orderItems) {
			bill += item.getPrice();
		}
		totalPrice = bill;
		return totalPrice;
	}
	
	

}
