package application;

public enum AuthManager {
	INSTANCE;

	private boolean auth = false;
	private int arrListPos = -1;

	private AuthManager() {
	}

	public static AuthManager getInstance() {
		return INSTANCE;
	}

	public void login(int position) {
		auth = true;
		arrListPos = position;
	}

	public void logout() {
		auth = false;
		arrListPos = -1;
	}

	public boolean getAuthStatus() {
		return auth;
	}
	
	public int getArrayListPos() {
		return arrListPos;
	}
	
}
