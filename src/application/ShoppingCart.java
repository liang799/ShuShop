package application;

import java.util.HashMap;

public class ShoppingCart {
	private HashMap<Book, Integer> cart;
	
	public void setCart(HashMap<Book, Integer> c) {
		cart = c;
	}
	
	public HashMap<Book, Integer> getCart() {
		return cart;
	}

}
