package ca.ubc.cs304.model;

public class Customer {

	private final char[] customerID;
	private final char[] customerName;
	private final int customerAge;
	private final char[] creditCardNumber;
	private final char[] phoneNumber;

	public Customer(char[] customerID, char[] customerName, int customerAge, char[] creditCardNumber, char[] phoneNumber) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAge = customerAge;
		this.creditCardNumber = creditCardNumber;
		this.phoneNumber = phoneNumber;
	}

	public char[] getCustomerID() {
		return customerID;
	}

	public char[] getCustomerName() {
		return customerName;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public char[] getCreditCardNumber() {
		return creditCardNumber;
	}

	public char[] getPhoneNumber() {
		return phoneNumber;
	}
}
