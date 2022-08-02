package application;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ImportController implements Initializable {
	@FXML
	private TableColumn<Book, Double> csvPriceCol;

	@FXML
	private TableView<Book> csvTable;

	@FXML
	private TableColumn<Book, String> csvTextbookCol;

	@FXML
	private TextField locationTextView;

	@FXML
	private TextField rootTextView;

	String path = System.getProperty("user.dir") + "/books.csv";

	@FXML
	void onUpdateButtonClicked(ActionEvent event) {
		path = System.getProperty("user.dir") + "/" + locationTextView.getText();
		System.out.println(path);
		csvTable.setItems(getBooks());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rootTextView.setText(System.getProperty("user.dir") + "\\");
		rootTextView.setDisable(true);
		locationTextView.setText("books.csv");
		csvTextbookCol.setCellValueFactory(new PropertyValueFactory<Book, String>("moduleCode"));
		csvPriceCol.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
		csvTable.setItems(getBooks());
	}

	public ObservableList<Book> getBooks() {
		CsvFile.getInstance().setData(readCSV(path));
		ObservableList<Book> list = FXCollections.observableArrayList(CsvFile.getInstance().getData());
		return list;
	}

	public ArrayList<Book> readCSV(String location) {
		ArrayList<Book> csvData = new ArrayList<Book>();
		try (BufferedReader br = new BufferedReader(new FileReader(location))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					String[] values = line.split(",");
					System.out.println(values[0] + " " + values[1]);
					Book b = new Book(values[0], Double.parseDouble(values[1]));
					csvData.add(b);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Please ensure the content of the csv is as follows: \nmoduleCode, price",
							"Wrong File Format", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The system cannot find the path specified.\nbooks.csv will be created",
					"Invalid Path", JOptionPane.WARNING_MESSAGE);
			locationTextView.setText("books.csv");
			newCSV(System.getProperty("user.dir") + "\\books.csv");
		}

		return csvData;
	}

	public void newCSV(String filePath) {
		File file = new File(filePath);
		try {
			if (file.createNewFile()) {
				JOptionPane.showMessageDialog(null, filePath + " created!");
			} else {
				JOptionPane.showMessageDialog(null, "books.csv already exists. Please press the update button");
			}
		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}
	}
}
