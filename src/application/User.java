package application;

public abstract class User {
	protected String userName, password;

	protected User(String uid, String passwd) {
		userName = uid;
		password = passwd;
	}

	public String getName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean authenticate(User user, String uid, String passwd) {
		boolean auth = false;
		if (user.getName() == uid && user.getPassword() == passwd) {
			auth = true;
		}
		return auth;
	}
}
