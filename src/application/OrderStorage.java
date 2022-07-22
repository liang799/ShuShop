package application;

import java.util.ArrayList;

public enum OrderStorage {
	INSTANCE(new ArrayList<CartItem>());
	private ArrayList<CartItem> orderItems;
	
	private OrderStorage(ArrayList<CartItem> c) {
		orderItems = c;
	}
	
	public OrderStorage getInstance() {
		return INSTANCE;
	}
	
	public void joinOrder(ArrayList<CartItem> c) {
		orderItems.addAll(c);
	}
	
	public ArrayList<CartItem> getOrders() {
		return orderItems;
	}

	
	
}
