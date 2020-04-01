package ca.ubc.cs304.model;

public class Employee {

	private final char[] staffID;
	private final char[] name;
	private final int payrollAccountNumber;
	private final int salary;
	private final char[] workShift;
	private final char[] managerStaffID;

	public Employee(char[] staffID, char[] name, int payrollAccountNumber, int salary, char[] workShift, char[] managerStaffID) {
		this.staffID = staffID;
		this.name = name;
		this.payrollAccountNumber = payrollAccountNumber;
		this.salary = salary;
		this.workShift = workShift;
		this.managerStaffID = managerStaffID;
	}

	public char[] getStaffID() {
		return staffID;
	}

	public char[] getName() {
		return name;
	}

	public int getPayrollAccountNumber() {
		return payrollAccountNumber;
	}

	public int getSalary() {
		return salary;
	}

	public char[] getWorkShift() {
		return workShift;
	}

	public char[] getManagerStaffID() {
		return managerStaffID;
	}
}
