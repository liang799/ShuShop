package application;

import java.util.ArrayList;

public enum CsvFile {
	INSTANCE();

	ArrayList<Book> csvData;

	public static CsvFile getInstance() {
		return INSTANCE;
	}
	
	CsvFile() { }
	
	public ArrayList<Book> getData() {
		return csvData;
	}
	
	public void setData(ArrayList<Book> b) {
		csvData = b;
	}
}
