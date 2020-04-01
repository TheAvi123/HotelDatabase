package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Calls {
	private final char[] receptionist_staff_id;
	private final char[] customer_id;

	public Calls(char[] receptionist_staff_id, char[] customer_id) {
		this.receptionist_staff_id = receptionist_staff_id;
		this.customer_id = customer_id;
	}

	public char[] getReceptionistStaffID() {
		return receptionist_staff_id;
	}

	public char[] getCustomerID() {
		return customer_id;
	}

}
