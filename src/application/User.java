package application;

public class User {
	private String userName, passwd;
	private boolean important; //big wheel?

	private User(String uid, String password, boolean importance) {
		userName = uid;
		passwd = password;
		important = importance;
	}
	
	public boolean getImportance() {
		return important;
	}

	public static boolean checkImportance(User user) {
		return user.getImportance();
	}
}
