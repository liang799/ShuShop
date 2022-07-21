package application;

import java.util.ArrayList;
import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		ArrayList<Customer> customers = new ArrayList<Customer>(); // create 2 files: 1 for users.txt, another for
																	// admin.txt
		String[] userNames = { "ned", "47" };
		String[] password = { "456", "47" };

		for (int i = 0; i < 2; i++) {
			Customer user = new Customer(userNames[i], password[i]);
			customers.add(user);
		}

		AuthManager session = new AuthManager();
		for (Customer cust : customers) {
			session.authenticate(cust, "ned", "456");
			if (session.getAuthStatus() == true) {
				gotoShopping(cust);
				System.out.println("Hit!");
			} else {
				System.out.println("Miss!");
			}
		}
	}

	private static void gotoShopping(Customer c) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 3; i++) {
			Book book = new Book("MS0421", 4.20);
			books.add(book);
		}

		Random r = new Random();
		double balance = Double.valueOf(r.nextInt(50));
		Card card = new Card("4242 4242 4242 4242", "12/34", "111", balance);
		c.setCard(card);
		Order bill = new Order(c, books);

		PaymentManager.payWith(c.getCard(), bill.getTotalPrice());
		System.out.println(c.getCard().getBalance());

	}

	private static void gotoAdminPanel(int j, ArrayList<User> users) {
		// TODO Auto-generated method stub
	}
}
