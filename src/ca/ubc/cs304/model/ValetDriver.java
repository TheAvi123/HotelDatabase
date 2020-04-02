package ca.ubc.cs304.model;

public class ValetDriver {
	private final char[] employeeStaffID;
	private final int drivingLicenseNumber;
	private final int payrollAccountNumber;
	private final int salary;
	private final char[] workShift;
	private final char[] managerStaffID;

	public ValetDriver(char[] staffID, char[] name, int payrollAccountNumber, int salary, char[] workShift, char[] managerStaffID) {
		this.staffID = staffID;
		this.name = name;
		this.payrollAccountNumber = payrollAccountNumber;
		this.salary = salary;
		this.workShift = workShift;
		this.managerStaffID = managerStaffID;
	}


}
