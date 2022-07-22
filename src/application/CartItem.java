package application;

public class CartItem {
	private Book book;
	private int quantity;
	
	public CartItem(Book b, int qty) {
		book = b;
		quantity = qty;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int q) {
		quantity = q;
	}
	
	public Book getBook() {
		return book;
	}

}
