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
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersController implements Initializable {

	@FXML
	private TableColumn<CartItem, String> codeCol;

	@FXML
	private TableView<CartItem> ordersTable;

	@FXML
	private TableColumn<CartItem, Double> priceCol;

	@FXML
	private TableColumn<CartItem, Integer> qtyCol;

	@FXML
	void onExport(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		codeCol.setCellValueFactory(new PropertyValueFactory<CartItem, String>("moduleCode"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("price"));
		ObservableList<CartItem> items = FXCollections.observableArrayList(OrderStorage.getInstance().getOrders());
		ordersTable.setItems(items);
	}
}
