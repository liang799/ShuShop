package application;

public class Card {
	private String number, cv, date;
	private double balance;
	
	Card(String num, String val, String d, double b) {
		number = num;
		cv = val;
		date = d;
		balance = b;
	}

	public double getBalance() {
		return balance;
	}
	
	public void updateBalance(double deducted) {
		balance = deducted;
	}
}
