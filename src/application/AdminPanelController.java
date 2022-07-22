package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminPanelController {

	@FXML
	private Button logoutButton;

	@FXML
	void onImportClicked(ActionEvent event) {
		gotoLocation(event, "Import.fxml");
	}

	@FXML
	void onLogoutClicked(ActionEvent event) {
		Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		AuthManager.getInstance().logout();
		gotoLocation(event, "Login.fxml");
	}

	@FXML
	void onOrderClicked(ActionEvent event) {
		gotoLocation(event, "Orders.fxml");
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
}
