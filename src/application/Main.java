package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Read Customers
		ArrayList<Customer> customer = new ArrayList<Customer>();
		Map<String, String> users = readUsers("students.txt");
		Customer c;
		for (Map.Entry<String, String> set : users.entrySet()) {
			System.out.println(set.getKey() + " = " + set.getValue());
			c = new Customer(set.getKey(), set.getValue());
			customer.add(c);
		}
		if (!customer.isEmpty())
			AccountVault.getInstance().setCustomers(customer);

		// Read SuperUsers
		ArrayList<SuperUser> superUsers = new ArrayList<SuperUser>();
		users = readUsers("admin.txt");
		SuperUser s;
		for (Map.Entry<String, String> set : users.entrySet()) {
			System.out.println(set.getKey() + " = " + set.getValue());
			s = new SuperUser(set.getKey(), set.getValue());
			superUsers.add(s);
		}
		if (!superUsers.isEmpty())
			AccountVault.getInstance().setSuperUsers(superUsers);

		launch(args);
	}

	public static HashMap<String, String> readUsers(String file) {
		HashMap<String, String> users = new HashMap<String, String>();
		String u, p;
		try {
			File myFile = new File(file);
			Scanner sc = new Scanner(myFile);
			while (sc.hasNextLine()) {
				u = sc.nextLine();
				p = sc.nextLine();
				users.put(u, p);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Please place file in location:\n/path/to/ShuShop/" + file, file + " not found!", JOptionPane.ERROR_MESSAGE);
		}
		return users;
	}
}
