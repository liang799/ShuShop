package application;

public class User {
	private String uid, gid, passwd;

	private User(String name, String password) {
		uid = name;
		passwd = password;
	}
	
	public void groupAdd(String group) {
		gid = group;
	}

}
