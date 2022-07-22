package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookController implements Initializable {

	@FXML
	private TableView<Book> resultTable;

	@FXML
	private TableColumn<Book, String> bookResult;

	@FXML
	private TableColumn<Book, Double> priceResult;

	@FXML
	private TextField searchBar;
	
	FilteredList<Book> flBook;

	@FXML
	void search(ActionEvent event) {
		// https://stackoverflow.com/questions/47559491/making-a-search-bar-in-javafx
		flBook.setPredicate(p -> p.getModuleCode().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookResult.setCellValueFactory(new PropertyValueFactory<Book, String>("moduleCode"));
		priceResult.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));

		flBook = new FilteredList<Book>(getBooks(), p -> true);// Pass the data to a filtered list
		resultTable.setItems(flBook);// Set the table's items using the filtered list
	}

	public ObservableList<Book> getBooks() {
		ObservableList<Book> list = FXCollections.observableArrayList(CsvFile.getInstance().getData());
		return list;
	}

}
