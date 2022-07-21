package application;

import java.util.ArrayList;

public enum AccountVault {
	INSTANCE;

	private AccountVault() {
	}

	public static AccountVault getInstance() {
		return INSTANCE;
	}

//	private SuperUser admin;
	private ArrayList<Customer> customers;
	private ArrayList<SuperUser> superUsers;

//	public void setAdmin(SuperUser admin) {
//		this.admin = admin;
//	}
//
//	public SuperUser getAdmin() {
//		return admin;
//	}

	public void setCustomers(ArrayList<Customer> c) {
		customers = c;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setSuperUsers(ArrayList<SuperUser> s) {
		superUsers = s;
	}

	public ArrayList<SuperUser> getSuperUsers() {
		return superUsers;
	}
}
