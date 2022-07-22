package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty moduleCode;
	private SimpleDoubleProperty price;

	public Book(String string, double d) {
		moduleCode = new SimpleStringProperty(string);
		price = new SimpleDoubleProperty(d);
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public String getModuleCode() {
		return moduleCode.get();
	}
	
	public SimpleStringProperty getWrappedModuleCode() {
		return moduleCode;
	}
	
	public SimpleDoubleProperty getWrappedPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return moduleCode + " $" + price;
	}
}
