package application;

public class Customer extends User {
	private Card card;

	protected Customer(String uid, String passwd) {
		super(uid, passwd);
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	public Card getCard() {
		return card;
	}
}
