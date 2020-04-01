package ca.ubc.cs304.model;

public class Assists {

	private final String customer_id;
	private final String bellhopStaffID;

	public Assists(String customer_id, String bellhopStaffID) {
		this.customer_id = customer_id;
		this.bellhopStaffID = bellhopStaffID;
	}

	public String getCustomerID() {
		return customer_id;
	}

	public String getBellhopStaffID() {
		return bellhopStaffID;
	}
}
