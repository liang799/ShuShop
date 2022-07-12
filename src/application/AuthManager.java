package application;

public class AuthManager {
	private boolean auth = false;

	public void authenticate(User user, String uid, String passwd) {
		String encoded = HashGen.hashPassword(user.getSalt() + passwd);
		if (user.getName().equals(uid) && user.getHashedPassword().equals(encoded))
			auth = true;
	}
	
	public void logout() {
		auth = false;
	}
	
	public boolean getAuthStatus() {
		return auth;
	}
}
