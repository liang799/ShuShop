package application;

import java.util.Random;

public class Card {
	private String number, cv, date;
	private double balance;
	private Random balanceGen;
	
	Card(String num, String val, String d) {
		number = num;
		cv = val;
		date = d;
		balanceGen = new Random();
		balance = Double.valueOf(balanceGen.nextInt(50));
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void updateBalance(double deducted) {
		balance = deducted;
	}
}
