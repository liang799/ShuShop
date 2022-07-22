package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class BookController implements Initializable {

	@FXML
	private TableView<Book> resultTable;

	@FXML
	private TextField quanText;

	@FXML
	private Text bookName;

	@FXML
	private TableColumn<Book, String> bookResult;

	@FXML
	private TableColumn<Book, Double> priceResult;

	@FXML
	private TextField searchBar;

	@FXML
	private TableView<CartItem> cartTable;

	@FXML
	private TableColumn<CartItem, String> codeCol;

	@FXML
	private TableColumn<CartItem, Double> priceCol;

	@FXML
	private TableColumn<CartItem, Integer> qtyCol;

	FilteredList<Book> flBook;
	Book selectedBk;
	ArrayList<CartItem> shopCart = new ArrayList<>();

	@FXML
	void search(ActionEvent event) {
		// https://stackoverflow.com/questions/47559491/making-a-search-bar-in-javafx
		flBook.setPredicate(p -> p.getModuleCode().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
	}

	@FXML
	void addToCart(ActionEvent event) {
		int quantity = Integer.parseInt(quanText.getText());
		System.out.println(quanText.getText());
		CartItem ci = new CartItem(selectedBk, quantity);
		shopCart.add(ci);
		ObservableList<CartItem> items = FXCollections.observableArrayList(shopCart);
		cartTable.setItems(items);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Search Results Table
		resultTable.setRowFactory(tv -> {
			TableRow<Book> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					selectedBk = row.getItem();
					System.out.println("Double click on: " + selectedBk.getModuleCode());
					bookName.setText(selectedBk.getModuleCode());
				}
			});
			return row;
		});

		bookResult.setCellValueFactory(new PropertyValueFactory<Book, String>("moduleCode"));
		priceResult.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));

		flBook = new FilteredList<Book>(getBooks(), p -> true);// Pass the data to a filtered list
		resultTable.setItems(flBook);// Set the table's items using the filtered list

		// Shopping Cart Table
		codeCol.setCellValueFactory(new PropertyValueFactory<CartItem, String>("moduleCode"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("price"));
		ObservableList<CartItem> items = FXCollections.observableArrayList(shopCart);
		cartTable.setItems(items);
	}

	public ObservableList<Book> getBooks() {
		ObservableList<Book> list = FXCollections.observableArrayList(CsvFile.getInstance().getData());
		return list;
	}

}
