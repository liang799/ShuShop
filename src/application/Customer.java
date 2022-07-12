package application;

public class Customer extends User {
	private Card card;

	protected Customer(String uid, String passwd) {
		super(uid, passwd);
	}

	public void setupCard(String cardNum, String date, String cv) {
		card = new Card(cardNum, date, cv);
	}
	
	public Card getCard() {
		return card;
	}
}
