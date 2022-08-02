package application;

import java.util.ArrayList;

public enum BookList {
	INSTANCE();

	ArrayList<Book> csvData;

	public static BookList getInstance() {
		return INSTANCE;
	}
	
	BookList() { }
	
	public ArrayList<Book> getData() {
		return csvData;
	}
	
	public void setData(ArrayList<Book> b) {
		csvData = b;
	}
}
