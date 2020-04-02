//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class ValetDriver extends Table {
//
//	private final char[] employeeStaffID;
//	private final int drivingLicenseNumber;
//
//	public ValetDriver(char[] employeeStaffID, int drivingLicenseNumber) {
//		this.employeeStaffID = employeeStaffID;
//		this.drivingLicenseNumber = drivingLicenseNumber;
//	}
//
//	public char[] getEmployeeStaffID() {
//		return employeeStaffID;
//	}
//
//	public int getDrivingLicenseNumber() {
//		return drivingLicenseNumber;
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
