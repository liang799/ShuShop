package application;

import java.util.ArrayList;

public enum CsvFile {
	INSTANCE("C:/Users/User/eclipse-workspace/ShuShop/b.csv");

	String path;
	ArrayList<Book> csvData = new ArrayList<Book>();

	public static CsvFile getInstance() {
		return INSTANCE;
	}
	
	CsvFile(String location) {
		path = location;
	}
	
	public String getPath() {
		return path;
	}
	
	public void updatePath(String location) {
		path = location;
	}
	
	public ArrayList<Book> getData() {
		return csvData;
	}
	
	public void setData(ArrayList<Book> b) {
		csvData = b;
	}
}
