package application;

public class Book {
	private String moduleCode;
	private double price;

	public Book(String string, double d) {
		moduleCode = string;
		price = d;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getModuleCode() {
		return moduleCode;
	}
}
