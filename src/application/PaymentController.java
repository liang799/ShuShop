package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PaymentController {

	@FXML
    private Button payButton;

	public void transferData(double bill) {
		payButton.setText("Pay $" + String.valueOf(bill));
	}

}
