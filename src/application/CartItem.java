package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CartItem {
	private Book book;
	private int quantity;
	private StringProperty moduleCode;
	private DoubleProperty price;

	public CartItem(Book b, int qty) {
		book = b;
		quantity = qty;
		moduleCode = new SimpleStringProperty(b.getModuleCode());
		price = new SimpleDoubleProperty(b.getPrice());
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

	public final StringProperty moduleCodeProperty() {
		return moduleCode;
	}

	public final DoubleProperty priceProperty() {
		return price;
	}
	
	

}
