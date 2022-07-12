package application;

public class PaymentManager {
	public static boolean payWith(Card card, double amount) {
		boolean success = false;
		double temp = card.getBalance() - amount;
		if (temp > 0) {
			success = true;
			card.updateBalance(temp);
		}
		return success;
	}
}
