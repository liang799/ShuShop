package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Node;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		if (selectedBk == null) {
			JOptionPane.showMessageDialog(null, "To select a textbook, double click on the row in the left table");
		} else {
			int quantity = Integer.parseInt(quanText.getText());
			CartItem cartItem = new CartItem(selectedBk, quantity);
			shopCart.add(cartItem);
			ObservableList<CartItem> items = FXCollections.observableArrayList(shopCart);
			System.out.println("Size of shopping cart array is " + shopCart.size());
			JOptionPane.showMessageDialog(null, cartItem.getBook().getModuleCode() + " has been added to cart");
			cartTable.setItems(items);
		}
	}

	@FXML
	void onCheckout(ActionEvent event) {
		if (shopCart.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Shopping Cart is empty");
		} else {
			double bill = calculateBill(shopCart);
			String message = "Total amount payable is $" + String.valueOf(bill) + ". Would you like to checkout?";
			int reply = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				gotoPayment(event, bill, shopCart);
			}
		}
	}

	@FXML
	void onRemoveRow(ActionEvent event) {
		shopCart.remove(cartTable.getSelectionModel().getSelectedItem());
		cartTable.getItems().removeAll(cartTable.getSelectionModel().getSelectedItem());
		System.out.println("Size of shopping cart array is " + shopCart.size());
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

	public double calculateBill(ArrayList<CartItem> shopCart) {
		double bill = 0.0;
		for (CartItem item : shopCart) {
			bill += Double.valueOf(item.getQuantity()) * item.getBook().getPrice();
		}
		return bill;
	}

	public void gotoPayment(ActionEvent event, double bill, ArrayList<CartItem> c) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene2 = new Scene(root);
			Stage Window2 = new Stage();
			Window2.initModality(Modality.APPLICATION_MODAL);
			Window2.setScene(scene2);
			Window2.show();
			PaymentController controller = loader.getController();
			controller.transferData(bill, c);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Stage login = (Stage) ((Node) event.getSource()).getScene().getWindow();
		login.close();
	}

}
