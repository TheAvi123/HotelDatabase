//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Employee extends Table {
//
//	private final char[] staffID;
//	private final char[] name;
//	private final int payrollAccountNumber;
//	private final int salary;
//	private final char[] workShift;
//	private final char[] managerStaffID;
//
//	public Employee(char[] staffID, char[] name, int payrollAccountNumber, int salary, char[] workShift, char[] managerStaffID) {
//		this.staffID = staffID;
//		this.name = name;
//		this.payrollAccountNumber = payrollAccountNumber;
//		this.salary = salary;
//		this.workShift = workShift;
//		this.managerStaffID = managerStaffID;
//	}
//
//	public char[] getStaffID() {
//		return staffID;
//	}
//
//	public char[] getName() {
//		return name;
//	}
//
//	public int getPayrollAccountNumber() {
//		return payrollAccountNumber;
//	}
//
//	public int getSalary() {
//		return salary;
//	}
//
//	public char[] getWorkShift() {
//		return workShift;
//	}
//
//	public char[] getManagerStaffID() {
//		return managerStaffID;
//	}
//
//	@Override
//	public String getTableName() {
//		return null;
//	}
//
//	@Override
//	public String[] getPrimaryAttributes() {
//		return new String[0];
//	}
//
//	@Override
//	public void printTupleInfo(Table tuple) {
//
//	}
//
//	@Override
//	public void setAllStatementParameters(PreparedStatement ps) throws SQLException {
//
//	}
//}
