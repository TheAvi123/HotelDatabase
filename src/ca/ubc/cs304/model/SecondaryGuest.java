package ca.ubc.cs304.model;

public class SecondaryGuest {

	private final char[] customerID;
	private final char[] guestName;
	private final int guestAge;

	public SecondaryGuest(char[] customerID, char[] guestName, int guestAge) {
		this.customerID = customerID;
		this.guestName = guestName;
		this.guestAge = guestAge;
	}

	public char[] getCustomerID() {
		return customerID;
	}

	public char[] getGuestName() {
		return guestName;
	}

	public int getGuestAge() {
		return guestAge;
	}
}
