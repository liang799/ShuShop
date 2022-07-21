package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	@FXML
	void search(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookResult.setCellValueFactory(new PropertyValueFactory<Book, String>("moduleCode"));
		priceResult.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));

		resultTable.setItems(getBooks());

	}

	public ObservableList<Book> getBooks() {
		ObservableList<Book> list = FXCollections.observableArrayList();
		list.add(new Book("ET0730", 4.2));
		list.add(new Book("MS0730", 3.2));
		return list;
	}

}
