package ca.ubc.cs304.model;

public class ValetDriver {

	private final char[] employeeStaffID;
	private final int drivingLicenseNumber;

	public ValetDriver(char[] employeeStaffID, int drivingLicenseNumber) {
		this.employeeStaffID = employeeStaffID;
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public char[] getEmployeeStaffID() {
		return employeeStaffID;
	}

	public int getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}
}
