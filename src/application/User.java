package application;

public abstract class User {
	private String userName, hashedPasswd;
	private String salt = "nicesalt";

	protected User(String uid, String passwd) {
		userName = uid;
		hashedPasswd = HashGen.hashPassword(salt + passwd);
	}

	public String getName() {
		return userName;
	}

	public String getHashedPassword() {
		return hashedPasswd;
	}
	
	public String getSalt() {
		return salt;
	}
}
