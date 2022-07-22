package application;

import java.util.ArrayList;

public enum OrderManager {
	INSTANCE;

	private ArrayList<Order> orders;

	public OrderManager getInstance() {
		return INSTANCE;
	}

	private OrderManager() {
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> list) {
		orders = list;
	}
}
