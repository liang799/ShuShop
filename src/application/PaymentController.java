package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentController implements Initializable {

	@FXML
	private TextField cardNum;

	@FXML
	private TextField cvc;

	@FXML
	private DatePicker expiryDate;

	@FXML
	private Button payButton;

	double bill;
	ArrayList<CartItem> cart;

	public void transferData(double bill, ArrayList<CartItem> c) {
		payButton.setText("Pay $" + String.valueOf(bill));
		this.bill = bill;
		cart = c;
	}

	@FXML
	void onPay(ActionEvent event) {
		if (cardNum.getText().isEmpty() || expiryDate.getValue() == null || cvc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields");
		} else {
			if (AccountVault.getInstance().getCustomers().get(AuthManager.getInstance().getArrayListPos())
					.getCard() == null) {
				Random r = new Random();
				double balance = Double.valueOf(r.nextInt(50));
				System.out.println(String.valueOf(balance));
				Card card = new Card(cardNum.getText(), cvc.getText(), expiryDate.getValue().toString(), balance);
				AccountVault.getInstance().getCustomers().get(AuthManager.getInstance().getArrayListPos())
						.setCard(card);
			}
			if (PaymentManager.payWith(AccountVault.getInstance().getCustomers()
					.get(AuthManager.getInstance().getArrayListPos()).getCard(), bill)) {
				JOptionPane.showMessageDialog(null, "Payment Successful. You will be logged out.");
				System.out.println(AccountVault.getInstance().getCustomers()
						.get(AuthManager.getInstance().getArrayListPos()).getCard().getBalance());
				OrderStorage.getInstance().joinOrder(cart);

				Stage stage = (Stage) payButton.getScene().getWindow();
				stage.close();
				AuthManager.getInstance().logout();
				gotoLocation(event, "Login.fxml");
			} else {
				JOptionPane.showMessageDialog(null, "Insufficient balance");
			}
		}
	}

	public void gotoLocation(ActionEvent event, String location) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(location));
			Scene scene2 = new Scene(root);
			Stage Window2 = new Stage();
			Window2.initModality(Modality.APPLICATION_MODAL);
			Window2.setScene(scene2);
			Window2.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (AccountVault.getInstance().getCustomers().get(AuthManager.getInstance().getArrayListPos())
				.getCard() != null) {
			cardNum.setText("*****************");
			String date = AccountVault.getInstance().getCustomers().get(AuthManager.getInstance().getArrayListPos()).getCard().getDate();
			System.out.println(date);
			expiryDate.setValue(LocalDate.parse(date));
			cvc.setText("***");
		}
	}
}
