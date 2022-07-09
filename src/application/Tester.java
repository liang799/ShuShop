package application;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		SuperUser sudo = new SuperUser("Chad", "123");
		users.add(sudo);
		Student ned = new Student("ned", "456");
		users.add(ned);

		for (User i : users) {
			System.out.println(i.authenticate(i, "ned", "456"));
		}

	}

}
