package application;

public enum AuthManager {
	INSTANCE;

	private boolean auth = false;
	private int arrListPos = -1;
	private String type = "";

	private AuthManager() {
	}

	public static AuthManager getInstance() {
		return INSTANCE;
	}

	public void login(int position, String userType) {
		auth = true;
		arrListPos = position;
		type = userType;
	}

	public void logout() {
		auth = false;
		arrListPos = -1;
		type = "";
	}

	public boolean getAuthStatus() {
		return auth;
	}
	
}
