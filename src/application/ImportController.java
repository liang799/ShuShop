package application;

import java.io.BufferedReader;
import java.io.FileReader;
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
	void onUpdateButtonClicked(ActionEvent event) {
		CsvFile.getInstance().updatePath(locationTextView.getText());
		csvTable.setItems(getBooks());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		locationTextView.setText(CsvFile.getInstance().getPath());
		csvTextbookCol.setCellValueFactory(new PropertyValueFactory<Book, String>("moduleCode"));
		csvPriceCol.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
	}

	public ObservableList<Book> getBooks() {
		CsvFile.getInstance().setData(readCSV(CsvFile.getInstance().getPath()));
		ObservableList<Book> list = FXCollections.observableArrayList(CsvFile.getInstance().getData());
		return list;
	}

	public ArrayList<Book> readCSV(String location) {
		ArrayList<Book> csvData = new ArrayList<Book>();
		try (BufferedReader br = new BufferedReader(new FileReader(location))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println(values[0] + " " + values[1]);
				Book b = new Book(values[0], Double.parseDouble(values[1]));
				csvData.add(b);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The system cannot find the path specified", "Invalid Path",
					JOptionPane.ERROR_MESSAGE);
		}

		return csvData;
	}

}
