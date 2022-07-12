package application;

public class CsvFile {
	String path;
	
	CsvFile(String location) {
		path = location;
	}
	
	public String getPath() {
		return path;
	}
	
	public void updatePath(String location) {
		path = location;
	}
}
