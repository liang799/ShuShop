package application;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		ArrayList<User> users = new ArrayList<User>(); //create 2 files: 1 for users.txt, another for admin.txt
		String[] userNames = { "Admin", "ned" ,"47"};
		String[] password = { "123", "456", "47"};

		SuperUser admin = null;
		Customer user = null;
		for (int i = 0; i < 2; i++) {
			if (userNames[i].equals("Admin")) {
				admin = new SuperUser(userNames[i], password[i]);
				users.add(admin);
			} else {
				user = new Customer(userNames[i], password[i]);
				users.add(user);
			}
		}

		AuthManager session = new AuthManager();
	    for (int j = 0; j < users.size(); j++) {
	    	User i = users.get(j);
			session.authenticate(i, "ned", "456");
			if (session.getAuthStatus() == true) {
				if (i.getImportance() == true)
					gotoAdminPanel(j, users);
				else
					gotoShopping(j, users);
			}
		}

	}

	private static void gotoShopping(int j, ArrayList<User> users) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 3; i++) {
			Book book = new Book("MS0421", 4.20);
			books.add(book);
		}
		Customer c = (Customer) users.get(j);

		c.setupCard("4242 4242 4242 4242", "12/34", "111");
		Order bill = new Order(c, books);
		
		PaymentManager.payWith(c.getCard(), bill.getTotalPrice());
		System.out.println(c.getCard().getBalance());
		
	}

	private static void gotoAdminPanel(int j, ArrayList<User> users) {
		// TODO Auto-generated method stub
		
	}
}
