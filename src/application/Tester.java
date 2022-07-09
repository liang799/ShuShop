package application;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		SuperUser bigWheel = new SuperUser("Chad", "123");
		users.add(bigWheel);
		Student ned = new Student("ned", "456");
		users.add(ned);

		for (User i : users) {
			System.out.println(i.authenticate(i, "ned", "456"));
		}

		ArrayList<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 3; i++) {
			Book book = new Book("MS0421", 4.20);
			books.add(book);
		}

		double bill = Book.getTotalPrice(books);
		ned.setupCard("4242 4242 4242 4242", "12/34", "111");
		ned.pay(bill);
	}

}
