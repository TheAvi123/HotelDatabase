package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Assists {
	private final char[] customer_id;
	private final char[] bellhopStaffID;

	public Assists(char[] customer_id, char[] bellhopStaffID) {
		this.customer_id = customer_id;
		this.bellhopStaffID = bellhopStaffID;

	}

	public char[] getCustomerID() {
		return customer_id;
	}

	public char[] getBellhopStaffID() {
		return bellhopStaffID;
	}

}
