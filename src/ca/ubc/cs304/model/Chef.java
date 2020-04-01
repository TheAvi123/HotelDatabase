package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Chef {
	private final char[] employee_staff_id;
	private final char[] signature_dish;


	public Chef(char[] employee_staff_id, char[] signature_dish) {
		this.employee_staff_id = employee_staff_id;
		this.signature_dish = signature_dish;
	}

	public char[] getEmployeeStaffID() {
		return employee_staff_id;
	}

	public char[] getSignatureDish() {
		return signature_dish;
	}
}
