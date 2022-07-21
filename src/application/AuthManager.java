package application;

public enum AuthManager {
	INSTANCE;

	private boolean auth = false;

	private AuthManager() {
	}

	public static AuthManager getInstance() {
		return INSTANCE;
	}

	public void authenticate(User user, String uid, String passwd) {
		String encoded = HashGen.hashPassword(user.getSalt() + passwd);
		if (user.getName().equals(uid) && user.getHashedPassword().equals(encoded))
			auth = true;
		else
			auth = false;
	}

	public void logout() {
		auth = false;
	}

	public boolean getAuthStatus() {
		return auth;
	}
}
