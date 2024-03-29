package application;

import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button loginButton;

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField txtPwd;

	@FXML
	void onLoginClicked(ActionEvent event) {
		ArrayList<Customer> customers = AccountVault.getInstance().getCustomers();
		for (int i = 0; i < customers.size(); i++) {
			if (authenticate(customers.get(i), txtLogin.getText(), txtPwd.getText())) {
				AuthManager.getInstance().login(i);
				JOptionPane.showMessageDialog(null, "Login successful.");
				gotoLocation(event, "Books.fxml");
				break;
			}
		}

		ArrayList<SuperUser> superUsers = AccountVault.getInstance().getSuperUsers();
		for (int i = 0; i < superUsers.size(); i++) {
			if (authenticate(superUsers.get(i), txtLogin.getText(), txtPwd.getText())) {
				AuthManager.getInstance().login(i);
				JOptionPane.showMessageDialog(null, "Login successful.");
				gotoLocation(event, "AdminPanel.fxml");
				break;
			}
		}

		if (AuthManager.getInstance().getAuthStatus() == false)
			JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
	}

	public boolean authenticate(User user, String uid, String passwd) {
		boolean auth = false;
		String encoded = HashGen.hashPassword(user.getSalt() + passwd);
		if (user.getName().equals(uid) && user.getHashedPassword().equals(encoded))
			auth = true;

		return auth;
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
		Stage login = (Stage) ((Node) event.getSource()).getScene().getWindow();
		login.close();
	}
}
