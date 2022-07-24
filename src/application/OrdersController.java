package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
		try {
			writeExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		codeCol.setCellValueFactory(new PropertyValueFactory<CartItem, String>("moduleCode"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("price"));
		ObservableList<CartItem> items = FXCollections.observableArrayList(OrderStorage.getInstance().getOrders());
		ordersTable.setItems(items);
	}

	public void writeExcel() throws Exception {
		Writer writer = null;
		try {
			File file = new File("orders.csv");
			writer = new BufferedWriter(new FileWriter(file));
			for (CartItem c : OrderStorage.getInstance().getOrders()) {
				String text = c.getBook().getModuleCode() + ", " + c.getBook().getPrice() + ", " + c.getQuantity()
						+ "\n";
				System.out.println(text);
				writer.write(text);
			}
			JOptionPane.showMessageDialog(null, "Successfuly written to " + System.getProperty("user.dir") + "/orders.csv");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}
	}
}
