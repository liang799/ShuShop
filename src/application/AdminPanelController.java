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
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Import.fxml"));
			Scene scene2 = new Scene(root);
			Stage Window2 = new Stage();
			Window2.initModality(Modality.APPLICATION_MODAL);
			Window2.setScene(scene2);
			Window2.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void onLogoutClicked(ActionEvent event) {
		// get a handle to the stage
		Stage stage = (Stage) logoutButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	void onOrderClicked(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Orders.fxml"));
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
